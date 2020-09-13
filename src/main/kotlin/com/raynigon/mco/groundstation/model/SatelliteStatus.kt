package com.raynigon.mco.groundstation.model

import com.raynigon.unit_api.jpa.annotation.JpaUnit
import javax.measure.Quantity
import javax.measure.quantity.Acceleration
import javax.measure.quantity.Dimensionless
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Length
import javax.measure.quantity.Power
import javax.measure.quantity.Speed

data class SatelliteStatus(
    val distance: Quantity<Length>,
    val speed: Quantity<Speed>,
    val acceleration: Acceleration3D,
    val batteryPower: Quantity<ElectricCharge>,
    val batteryPowerPercent: Quantity<Dimensionless>,
    val solarPower: Quantity<Power>,
    val solarPowerPercent: Quantity<Dimensionless>,
    val freeMemory: Long,
    val freeMemoryPercent: Double
)

data class Acceleration3D(
    @JpaUnit("m/s²")
    val x: Quantity<Acceleration>,

    @JpaUnit("m/s²")
    val y: Quantity<Acceleration>,

    @JpaUnit("m/s²")
    val z: Quantity<Acceleration>
)
