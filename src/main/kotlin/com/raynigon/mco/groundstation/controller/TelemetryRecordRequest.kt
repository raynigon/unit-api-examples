package com.raynigon.mco.groundstation.controller

data class TelemetryRecordRequest(
    val rtt: Long,
    val fuel: TelemetryFuel,
    val sensors: TelemetrySensors,
    val energy: TelemetryEnergy,
    val computer: TelemetryComputer
)

data class TelemetryFuel(
    val nto: Double,
    val hydrazine: Double
)

data class TelemetrySensors(
    val gyroscope0: TelemetryAcceleration,
    val gyroscope1: TelemetryAcceleration,
    val sunSensor0: Double,
    val sunSensor1: Double
)

data class TelemetryAcceleration(
    val x: Double,
    val y: Double,
    val z: Double
)

data class TelemetryEnergy(
    val battery: Double,
    val solar: Double
)

data class TelemetryComputer(
    val clockSpeed: Long,
    val memory: TelemetryMemory
)

data class TelemetryMemory(
    val free: Long,
    val used: Long
)
