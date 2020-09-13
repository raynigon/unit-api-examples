package com.raynigon.mco.groundstation

import com.fasterxml.jackson.databind.ObjectMapper
import com.raynigon.mco.groundstation.model.ComputerStats
import com.raynigon.mco.groundstation.model.EnergyStats
import com.raynigon.mco.groundstation.model.FuelStats
import com.raynigon.mco.groundstation.model.SensorStats
import com.raynigon.mco.groundstation.model.TelemetryRecord
import com.raynigon.mco.groundstation.repo.TelemetryRecordRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import tech.units.indriya.quantity.Quantities.getQuantity
import tech.units.indriya.unit.Units.COULOMB
import tech.units.indriya.unit.Units.KILOGRAM
import tech.units.indriya.unit.Units.METRE_PER_SQUARE_SECOND
import tech.units.indriya.unit.Units.SECOND
import tech.units.indriya.unit.Units.WATT
import java.net.URI
import java.time.OffsetDateTime
import java.util.UUID
import javax.measure.MetricPrefix
import kotlin.collections.HashMap

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class ApplicationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var repository: TelemetryRecordRepository

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun sendRecord() {
        val json = "{\"rtt\":236958,\"fuel\":{\"nto\":61,\"hydrazine\":212},\"sensors\":{\"gyroscope0\":{\"x\":0.0002,\"y\":-0.0001,\"z\":0.0006},\"gyroscope1\":{\"x\":-0.0001,\"y\":0.0,\"z\":0.0001},\"sunSensor0\":0.8947,\"sunSensor1\":0.1293},\"energy\":{\"battery\":15.432,\"solar\":489.2},\"computer\":{\"clockSpeed\":5000000,\"memory\":{\"free\":17401750,\"used\":1472618}}}"
        val request = objectMapper.readValue(json, HashMap::class.java)
        val response = restTemplate.postForEntity(URI.create("/api/telemetry/"), request, HashMap::class.java)
        assertTrue(response.statusCode.is2xxSuccessful)
        assertEquals(1, repository.count())
    }

    @Test
    fun getStatus() {
        // given:
        val now = OffsetDateTime.now()
        val record0 = TelemetryRecord(
                id = UUID.randomUUID(),
                recorded = now.minusMinutes(10),
                rtt = getQuantity(236958, SECOND.prefix(MetricPrefix.MILLI)),
                fuel = FuelStats(getQuantity(61.0, KILOGRAM), getQuantity(212.0, KILOGRAM)),
                sensors = SensorStats(
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        0.0,
                        0.0
                ),
                energy = EnergyStats(getQuantity(15.432 / 3600.0, COULOMB), getQuantity(489.2, WATT)),
                computer = ComputerStats(5000000, 17401750, 1472618)
        )
        val record1 = TelemetryRecord(
                id = UUID.randomUUID(),
                recorded = now.minusMinutes(5),
                rtt = getQuantity(236962, SECOND.prefix(MetricPrefix.MILLI)),
                fuel = FuelStats(getQuantity(61.0, KILOGRAM), getQuantity(212.0, KILOGRAM)),
                sensors = SensorStats(
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        getQuantity(0.0, METRE_PER_SQUARE_SECOND),
                        0.0,
                        0.0
                ),
                energy = EnergyStats(getQuantity(15.432 / 3600.0, COULOMB), getQuantity(489.2, WATT)),
                computer = ComputerStats(5000000, 17401750, 1472618)
        )
        repository.saveAll(listOf(record0, record1))

        // when:
        val response = restTemplate.getForObject(URI.create("/api/telemetry/"), HashMap::class.java)

        // then:
        assertTrue(3.55E7 < (response["distance"] as Double))
        assertTrue(3.56E7 > (response["distance"] as Double))

        assertTrue(7195 < (response["speed"] as Double))
        assertTrue(7196 > (response["speed"] as Double))

        assertTrue(95.0 < (response["batteryPowerPercent"] as Double))
        assertTrue(100.0 > (response["batteryPowerPercent"] as Double))

        assertTrue(48.0 < (response["solarPowerPercent"] as Double))
        assertTrue(50.0 > (response["solarPowerPercent"] as Double))
    }
}
