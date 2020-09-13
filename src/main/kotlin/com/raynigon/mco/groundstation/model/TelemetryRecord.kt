package com.raynigon.mco.groundstation.model

import com.raynigon.unit_api.jpa.annotation.JpaUnit
import com.raynigon.unit_api.jpa.type.QuantityType
import org.hibernate.annotations.TypeDef
import java.time.OffsetDateTime
import java.util.UUID
import javax.measure.Quantity
import javax.measure.quantity.Acceleration
import javax.measure.quantity.ElectricCharge
import javax.measure.quantity.Mass
import javax.measure.quantity.Power
import javax.measure.quantity.Time
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "telemetry_record")
@TypeDef(
    name = "quantity",
    typeClass = QuantityType::class,
    defaultForType = Quantity::class
)
data class TelemetryRecord(
    @Id
    @Column(name = "id")
    val id: UUID,

    @Column(name = "recorded")
    val recorded: OffsetDateTime,

    @JpaUnit("ms")
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
    @JpaUnit("kg")
    @Column(name = "fuel_nto")
    val nto: Quantity<Mass>,

    @JpaUnit("kg")
    @Column(name = "fuel_hydrazine")
    val hydrazine: Quantity<Mass>
)

@Embeddable
data class SensorStats(
    @JpaUnit("m/s²")
    @Column(name = "sensors_gyroscope0_x")
    val gyroscope0X: Quantity<Acceleration>,

    @JpaUnit("m/s²")
    @Column(name = "sensors_gyroscope0_y")
    val gyroscope0Y: Quantity<Acceleration>,

    @JpaUnit("m/s²")
    @Column(name = "sensors_gyroscope0_z")
    val gyroscope0Z: Quantity<Acceleration>,

    @JpaUnit("m/s²")
    @Column(name = "sensors_gyroscope1_x")
    val gyroscope1X: Quantity<Acceleration>,

    @JpaUnit("m/s²")
    @Column(name = "sensors_gyroscope1_y")
    val gyroscope1Y: Quantity<Acceleration>,

    @JpaUnit("m/s²")
    @Column(name = "sensors_gyroscope1_z")
    val gyroscope1Z: Quantity<Acceleration>,

    @Column(name = "sensors_sun_sensor_0")
    val sunSensor0: Double,

    @Column(name = "sensors_sun_sensor_1")
    val sunSensor1: Double
)

@Embeddable
data class EnergyStats(
    @JpaUnit("Ah")
    @Column(name = "energy_battery")
    val battery: Quantity<ElectricCharge>,

    @JpaUnit("W")
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
