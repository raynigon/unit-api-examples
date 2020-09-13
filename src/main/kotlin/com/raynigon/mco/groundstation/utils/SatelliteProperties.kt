package com.raynigon.mco.groundstation.utils

import tech.units.indriya.quantity.Quantities
import tech.units.indriya.unit.Units
import javax.measure.Quantity
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Power

const val MCO_FUEL_NTO_MAX = 61
const val MCO_FUEL_HYDRAZINE_MAX = 230
val MCO_ENERGY_BATTERY_MAX: Quantity<ElectricCharge> = Quantities.getQuantity(16.0 / 3600, Units.COULOMB)
val MCO_ENERGY_SOLAR_MAX: Quantity<Power> = Quantities.getQuantity(1000, Units.WATT)
const val MCO_COMPUTER_MEMORY_BYTES_MAX = 18874368
