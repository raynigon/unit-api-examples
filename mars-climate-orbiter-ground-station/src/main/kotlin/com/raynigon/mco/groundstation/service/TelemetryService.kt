package com.raynigon.mco.groundstation.service

import com.raynigon.mco.groundstation.model.Acceleration3D
import com.raynigon.mco.groundstation.model.SatelliteStatus
import com.raynigon.mco.groundstation.model.TelemetryRecord
import com.raynigon.mco.groundstation.repo.TelemetryRecordRepository
import com.raynigon.mco.groundstation.utils.MCO_COMPUTER_MEMORY_BYTES_MAX
import com.raynigon.mco.groundstation.utils.MCO_ENERGY_BATTERY_MAX
import com.raynigon.mco.groundstation.utils.MCO_ENERGY_SOLAR_MAX
import com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.MetrePerSecond
import com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.Second
import com.raynigon.unit.api.kotlin.div
import com.raynigon.unit.api.kotlin.minus
import com.raynigon.unit.api.kotlin.times
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import javax.measure.Quantity
import javax.measure.quantity.Dimensionless
import javax.measure.quantity.Length
import javax.measure.quantity.Speed

val LIGHTSPEED: Quantity<Speed> = MetrePerSecond(299792458)

interface TelemetryService {

    fun record(record: TelemetryRecord)

    fun calculateStatus(): SatelliteStatus
}

@Service
class TelemetryServiceImpl(private val repository: TelemetryRecordRepository) : TelemetryService {

    override fun record(record: TelemetryRecord) {
        repository.save(record)
    }

    override fun calculateStatus(): SatelliteStatus {
        if (repository.count() < 2) throw RuntimeException("Unable to calculate Status without records")
        val records = repository.findAll(Sort.by("recorded").descending())
        val latest = records[0]
        val previous = records[1]

        val distance = calculateDistance(latest)
        val speed = calculateSpeed(previous, latest)
        val acceleration = calculateAcceleration(latest)

        return SatelliteStatus(
            distance = distance,
            speed = speed,
            acceleration = acceleration,
            batteryPower = latest.energy.battery,
            batteryPowerPercent = (latest.energy.battery / MCO_ENERGY_BATTERY_MAX) as Quantity<Dimensionless>,
            solarPower = latest.energy.solar,
            solarPowerPercent = (latest.energy.solar / MCO_ENERGY_SOLAR_MAX) as Quantity<Dimensionless>,
            freeMemory = latest.computer.freeMemory,
            freeMemoryPercent = latest.computer.freeMemory.toDouble() / MCO_COMPUTER_MEMORY_BYTES_MAX.toDouble()
        )
    }

    private fun calculateAcceleration(record: TelemetryRecord): Acceleration3D {
        return Acceleration3D(
            x = record.sensors.gyroscope0X,
            y = record.sensors.gyroscope0Y,
            z = record.sensors.gyroscope0Z
        )
    }

    private fun calculateSpeed(first: TelemetryRecord, second: TelemetryRecord): Quantity<Speed> {
        val distance0 = calculateDistance(first)
        val distance1 = calculateDistance(second)
        val timestamp0 = Second(first.recorded.toInstant().toEpochMilli() / 1000.0)
        val timestamp1 = Second(second.recorded.toInstant().toEpochMilli() / 1000.0)

        val deltaS = distance1 - distance0
        val deltaT = timestamp1 - timestamp0
        return (deltaS / deltaT) as Quantity<Speed>
    }

    private fun calculateDistance(record: TelemetryRecord): Quantity<Length> {
        return (LIGHTSPEED * record.rtt / 2.0) as Quantity<Length>
    }
}
