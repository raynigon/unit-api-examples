package com.raynigon.mco.groundstation

import com.fasterxml.jackson.databind.ObjectMapper
import com.raynigon.mco.groundstation.model.ComputerStats
import com.raynigon.mco.groundstation.model.EnergyStats
import com.raynigon.mco.groundstation.model.FuelStats
import com.raynigon.mco.groundstation.model.SensorStats
import com.raynigon.mco.groundstation.model.TelemetryRecord
import com.raynigon.mco.groundstation.repo.TelemetryRecordRepository
import com.raynigon.mco.groundstation.utils.coulomb
import com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.Kilogram
import com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.MetrePerSquaredSecond
import com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.MilliSecond
import com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.Watt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import java.net.URI
import java.time.OffsetDateTime
import java.util.UUID

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
    @Disabled("We need to have the units in the unit api instead of locally defined")
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
            rtt = MilliSecond(236958),
            fuel = FuelStats(Kilogram(61.0), Kilogram(212.0)),
            sensors = SensorStats(
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                0.0,
                0.0
            ),
            energy = EnergyStats(coulomb(15.432 / 3600.0), Watt(489.2)),
            computer = ComputerStats(5000000, 17401750, 1472618)
        )
        val record1 = TelemetryRecord(
            id = UUID.randomUUID(),
            recorded = now.minusMinutes(5),
            rtt = MilliSecond(236962),
            fuel = FuelStats(Kilogram(61.0), Kilogram(212.0)),
            sensors = SensorStats(
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                MetrePerSquaredSecond(0.0),
                0.0,
                0.0
            ),
            energy = EnergyStats(coulomb(15.432 / 3600.0), Watt(489.2)),
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
