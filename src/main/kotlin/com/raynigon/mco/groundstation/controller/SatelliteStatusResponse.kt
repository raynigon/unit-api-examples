package com.raynigon.mco.groundstation.controller

import com.raynigon.mco.groundstation.model.Acceleration3D

data class SatelliteStatusResponse(
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
