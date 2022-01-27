package com.raynigon.mco.groundstation.controller

import com.raynigon.mco.groundstation.model.Acceleration3D
import com.raynigon.unit.api.core.units.si.dimensionless.Percent
import com.raynigon.unit.api.core.units.si.electrical.charge.AmpereHour
import com.raynigon.unit.api.core.units.si.length.Kilometre
import com.raynigon.unit.api.core.units.si.power.Watt
import com.raynigon.unit.api.core.units.si.speed.KilometrePerHour
import com.raynigon.unit.api.jackson.annotation.JsonUnit
import javax.measure.Quantity
import javax.measure.quantity.Dimensionless
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Length
import javax.measure.quantity.Power
import javax.measure.quantity.Speed

data class SatelliteStatusResponse(
    @JsonUnit(Kilometre::class)
    val distance: Quantity<Length>,

    @JsonUnit(KilometrePerHour::class)
    val speed: Quantity<Speed>,

    @JsonUnit(AmpereHour::class)
    val batteryPower: Quantity<ElectricCharge>,

    @JsonUnit(Percent::class)
    val batteryPowerPercent: Quantity<Dimensionless>,

    @JsonUnit(Watt::class)
    val solarPower: Quantity<Power>,

    @JsonUnit(Percent::class)
    val solarPowerPercent: Quantity<Dimensionless>,

    val freeMemory: Long,
    val freeMemoryPercent: Double,
    val acceleration: Acceleration3D,
)
