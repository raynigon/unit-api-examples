package com.raynigon.mco.groundstation.model

import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "telemetry_record")
data class TelemetryRecord(
    @Id
    @Column(name = "id")
    val id: UUID,

    @Column(name = "recorded")
    val recorded: OffsetDateTime,

    @Column(name = "rtt")
    val rtt: Long,

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
    @Column(name = "fuel_nto")
    val nto: Double,

    @Column(name = "fuel_hydrazine")
    val hydrazine: Double
)

@Embeddable
data class SensorStats(
    @Column(name = "sensors_gyroscope0_x")
    val gyroscope0X: Double,

    @Column(name = "sensors_gyroscope0_y")
    val gyroscope0Y: Double,

    @Column(name = "sensors_gyroscope0_z")
    val gyroscope0Z: Double,

    @Column(name = "sensors_gyroscope1_x")
    val gyroscope1X: Double,

    @Column(name = "sensors_gyroscope1_y")
    val gyroscope1Y: Double,

    @Column(name = "sensors_gyroscope1_z")
    val gyroscope1Z: Double,

    @Column(name = "sensors_sun_sensor_0")
    val sunSensor0: Double,

    @Column(name = "sensors_sun_sensor_1")
    val sunSensor1: Double
)

@Embeddable
data class EnergyStats(
    @Column(name = "energy_battery")
    val battery: Double,

    @Column(name = "energy_solar")
    val solar: Double
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
