package com.raynigon.mco.groundstation.controller

import com.raynigon.mco.groundstation.model.ComputerStats
import com.raynigon.mco.groundstation.model.EnergyStats
import com.raynigon.mco.groundstation.model.FuelStats
import com.raynigon.mco.groundstation.model.SatelliteStatus
import com.raynigon.mco.groundstation.model.SensorStats
import com.raynigon.mco.groundstation.model.TelemetryRecord
import com.raynigon.mco.groundstation.service.TelemetryService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime
import java.util.UUID

@RestController
@RequestMapping("/api/telemetry/")
class TelemetryController(
    private val service: TelemetryService
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun record(@RequestBody record: TelemetryRecordRequest) {
        service.record(mapToDomain(record))
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun status(): SatelliteStatusResponse {
        return mapToResponse(service.calculateStatus())
    }

    private fun mapToResponse(status: SatelliteStatus): SatelliteStatusResponse {
        return SatelliteStatusResponse(
            distance = status.distance,
            speed = status.speed,
            acceleration = status.acceleration,
            batteryPower = status.batteryPower,
            batteryPowerPercent = status.batteryPowerPercent,
            solarPower = status.solarPower,
            solarPowerPercent = status.solarPowerPercent,
            freeMemory = status.freeMemory,
            freeMemoryPercent = status.freeMemoryPercent
        )
    }

    private fun mapToDomain(record: TelemetryRecordRequest): TelemetryRecord {
        return TelemetryRecord(
            id = UUID.randomUUID(),
            recorded = OffsetDateTime.now(),
            rtt = record.rtt,
            fuel = FuelStats(
                nto = record.fuel.nto,
                hydrazine = record.fuel.hydrazine
            ),
            sensors = SensorStats(
                gyroscope0X = record.sensors.gyroscope0.x,
                gyroscope0Y = record.sensors.gyroscope0.y,
                gyroscope0Z = record.sensors.gyroscope0.z,
                gyroscope1X = record.sensors.gyroscope1.x,
                gyroscope1Y = record.sensors.gyroscope1.y,
                gyroscope1Z = record.sensors.gyroscope1.z,
                sunSensor0 = record.sensors.sunSensor0,
                sunSensor1 = record.sensors.sunSensor1
            ),
            energy = EnergyStats(
                battery = record.energy.battery,
                solar = record.energy.solar
            ),
            computer = ComputerStats(
                clockSpeed = record.computer.clockSpeed.value.toLong(),
                freeMemory = record.computer.memory.free,
                usedMemory = record.computer.memory.used
            )
        )
    }
}
