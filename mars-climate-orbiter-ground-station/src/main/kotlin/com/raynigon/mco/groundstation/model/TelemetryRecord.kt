package com.raynigon.mco.groundstation.model

import com.raynigon.unit.api.core.units.si.acceleration.MetrePerSquaredSecond
import com.raynigon.unit.api.core.units.si.electrical.charge.AmpereHour
import com.raynigon.unit.api.core.units.si.mass.Kilogram
import com.raynigon.unit.api.core.units.si.power.Watt
import com.raynigon.unit.api.core.units.si.time.MilliSecond
import com.raynigon.unit.api.jpa.annotation.JpaUnit
import com.raynigon.unit.api.jpa.type.QuantityType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import java.time.OffsetDateTime
import java.util.UUID
import javax.measure.Quantity
import javax.measure.quantity.Acceleration
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Mass
import javax.measure.quantity.Power
import javax.measure.quantity.Time

@Entity
@Table(name = "telemetry_record")
data class TelemetryRecord(
    @Id
    @Column(name = "id")
    val id: UUID,

    @Column(name = "recorded")
    val recorded: OffsetDateTime,

    @JpaUnit(MilliSecond::class)
    @Type(QuantityType::class)
    @Column(name = "rtt")
    val rtt: Quantity<Time>,

    @Embedded
    val fuel: FuelStats,

    @Embedded
    val sensors: SensorStats,

    @Embedded
    val energy: EnergyStats,

    @Embedded
    val computer: ComputerStats
)

@Embeddable
data class FuelStats(
    @JpaUnit(Kilogram::class)
    @Type(QuantityType::class)
    @Column(name = "fuel_nto")
    val nto: Quantity<Mass>,

    @JpaUnit(Kilogram::class)
    @Type(QuantityType::class)
    @Column(name = "fuel_hydrazine")
    val hydrazine: Quantity<Mass>
)

@Embeddable
data class SensorStats(
    @JpaUnit(MetrePerSquaredSecond::class)
    @Type(QuantityType::class)
    @Column(name = "sensors_gyroscope0_x")
    val gyroscope0X: Quantity<Acceleration>,

    @JpaUnit(MetrePerSquaredSecond::class)
    @Type(QuantityType::class)
    @Column(name = "sensors_gyroscope0_y")
    val gyroscope0Y: Quantity<Acceleration>,

    @JpaUnit(MetrePerSquaredSecond::class)
    @Type(QuantityType::class)
    @Column(name = "sensors_gyroscope0_z")
    val gyroscope0Z: Quantity<Acceleration>,

    @JpaUnit(MetrePerSquaredSecond::class)
    @Type(QuantityType::class)
    @Column(name = "sensors_gyroscope1_x")
    val gyroscope1X: Quantity<Acceleration>,

    @JpaUnit(MetrePerSquaredSecond::class)
    @Type(QuantityType::class)
    @Column(name = "sensors_gyroscope1_y")
    val gyroscope1Y: Quantity<Acceleration>,

    @JpaUnit(MetrePerSquaredSecond::class)
    @Type(QuantityType::class)
    @Column(name = "sensors_gyroscope1_z")
    val gyroscope1Z: Quantity<Acceleration>,

    @Column(name = "sensors_sun_sensor_0")
    val sunSensor0: Double,

    @Column(name = "sensors_sun_sensor_1")
    val sunSensor1: Double
)

@Embeddable
data class EnergyStats(
    @JpaUnit(AmpereHour::class)
    @Type(QuantityType::class)
    @Column(name = "energy_battery")
    val battery: Quantity<ElectricCharge>,

    @JpaUnit(Watt::class)
    @Type(QuantityType::class)
    @Column(name = "energy_solar")
    val solar: Quantity<Power>
)

@Embeddable
data class ComputerStats(
    @Column(name = "computer_clock_speed")
    val clockSpeed: Long,

    @Column(name = "computer_memory_free")
    val freeMemory: Long,

    @Column(name = "computer_memory_used")
    val usedMemory: Long
)
