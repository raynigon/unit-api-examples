package com.raynigon.mco.groundstation.controller

import com.raynigon.mco.groundstation.model.Acceleration3D
import com.raynigon.unit_api.jackson.annotation.JsonUnit
import javax.measure.Quantity
import javax.measure.quantity.Dimensionless
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Length
import javax.measure.quantity.Power
import javax.measure.quantity.Speed

data class SatelliteStatusResponse(
        @JsonUnit("km")
        val distance: Quantity<Length>,

        @JsonUnit("km/h")
        val speed: Quantity<Speed>,

        val acceleration: Acceleration3D,
        @JsonUnit("Ah")
        val batteryPower: Quantity<ElectricCharge>,

        @JsonUnit("%")
        val batteryPowerPercent: Quantity<Dimensionless>,

        @JsonUnit("W")
        val solarPower: Quantity<Power>,

        @JsonUnit("%")
        val solarPowerPercent: Quantity<Dimensionless>,

        val freeMemory: Long,
        val freeMemoryPercent: Double
)
