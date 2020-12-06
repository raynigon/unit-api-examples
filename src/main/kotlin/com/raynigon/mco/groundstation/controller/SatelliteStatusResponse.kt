package com.raynigon.mco.groundstation.controller

import com.raynigon.mco.groundstation.model.Acceleration3D
import com.raynigon.mco.groundstation.utils.Coulomb
import com.raynigon.unit_api.core.function.unitconverter.MultiplyConverter
import com.raynigon.unit_api.core.units.general.IUnit
import com.raynigon.unit_api.core.units.general.TransformedUnit
import com.raynigon.unit_api.core.units.si.SISystem
import com.raynigon.unit_api.core.units.si.dimensionless.Percent
import com.raynigon.unit_api.core.units.si.electrical.current.Ampere
import com.raynigon.unit_api.core.units.si.length.Kilometre
import com.raynigon.unit_api.core.units.si.power.Watt
import com.raynigon.unit_api.core.units.si.speed.KilometrePerHour
import com.raynigon.unit_api.core.units.si.time.Hour
import com.raynigon.unit_api.jackson.annotation.JsonUnit
import javax.measure.Quantity
import javax.measure.Unit
import javax.measure.quantity.Dimensionless
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Length
import javax.measure.quantity.Power
import javax.measure.quantity.Speed

// TODO use AmpereHour from unit api
@Suppress("UNCHECKED_CAST")
class AmpereHour :
    TransformedUnit<ElectricCharge>(
        "Ah",
        "Ampere hour",
        Hour().multiply(Ampere()) as Unit<ElectricCharge>,
        Coulomb(),
        MultiplyConverter.of(1.0)
    ),
    IUnit<ElectricCharge> {
    override fun getSystemId(): String {
        return SISystem.ID
    }

    override fun getQuantityType(): Class<ElectricCharge> {
        return ElectricCharge::class.java
    }

    override fun isSystemUnit() = false
}

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
