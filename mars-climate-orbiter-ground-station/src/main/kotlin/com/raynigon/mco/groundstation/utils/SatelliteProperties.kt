package com.raynigon.mco.groundstation.utils

import com.raynigon.unit.api.core.service.UnitsApiService
import com.raynigon.unit.api.core.units.general.AlternateUnit
import com.raynigon.unit.api.core.units.si.SISystem
import com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.Watt
import com.raynigon.unit.api.core.units.si.electrical.current.Ampere
import com.raynigon.unit.api.core.units.si.time.Second
import javax.measure.Quantity
import javax.measure.Unit
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Power

// TODO use Coulomb from Unit Api
@Suppress("UNCHECKED_CAST")
class Coulomb : AlternateUnit<ElectricCharge>(SISystem.ID, "C", "Coulomb", Second().multiply(Ampere()) as Unit<ElectricCharge>, ElectricCharge::class.java)

fun coulomb(value: Number): Quantity<ElectricCharge> {
    return UnitsApiService.quantity(value, Coulomb())
}

const val MCO_FUEL_NTO_MAX = 61
const val MCO_FUEL_HYDRAZINE_MAX = 230
val MCO_ENERGY_BATTERY_MAX: Quantity<ElectricCharge> = coulomb(16.0 / 3600) // COULOMB
val MCO_ENERGY_SOLAR_MAX: Quantity<Power> = Watt(1000)
const val MCO_COMPUTER_MEMORY_BYTES_MAX = 18874368
