package com.raynigon.mco.groundstation.controller

import com.raynigon.unit.api.core.units.si.time.MilliSecond
import com.raynigon.unit.api.jackson.annotation.JsonUnit
import javax.measure.Quantity
import javax.measure.quantity.Acceleration
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Frequency
import javax.measure.quantity.Mass
import javax.measure.quantity.Power
import javax.measure.quantity.Time

data class TelemetryRecordRequest(
    @JsonUnit(MilliSecond::class)
    val rtt: Quantity<Time>,
    val fuel: TelemetryFuel,
    val sensors: TelemetrySensors,
    val energy: TelemetryEnergy,
    val computer: TelemetryComputer,
)

data class TelemetryFuel(
    val nto: Quantity<Mass>,
    val hydrazine: Quantity<Mass>,
)

data class TelemetrySensors(
    val gyroscope0: TelemetryAcceleration,
    val gyroscope1: TelemetryAcceleration,
    val sunSensor0: Double,
    val sunSensor1: Double,
)

data class TelemetryAcceleration(
    val x: Quantity<Acceleration>,
    val y: Quantity<Acceleration>,
    val z: Quantity<Acceleration>,
)

data class TelemetryEnergy(
    val battery: Quantity<ElectricCharge>,
    val solar: Quantity<Power>,
)

data class TelemetryComputer(
    val clockSpeed: Quantity<Frequency>,
    val memory: TelemetryMemory,
)

data class TelemetryMemory(
    val free: Long,
    val used: Long,
)
