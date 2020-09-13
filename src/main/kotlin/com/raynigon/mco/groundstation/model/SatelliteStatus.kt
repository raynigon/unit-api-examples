package com.raynigon.mco.groundstation.model

data class SatelliteStatus(
    val distance: Double,
    val speed: Double,
    val acceleration: Acceleration3D,
    val batteryPower: Double,
    val batteryPowerPercent: Double,
    val solarPower: Double,
    val solarPowerPercent: Double,
    val freeMemory: Long,
    val freeMemoryPercent: Double
)

data class Acceleration3D(
    val x: Double,
    val y: Double,
    val z: Double
)
