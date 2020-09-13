CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table telemetry_record
(
    id                   uuid        not null,
    recorded             timestamptz not null,
    rtt                  bigint      not null,
    fuel_nto             float       not null,
    fuel_hydrazine       float       not null,
    sensors_gyroscope0_x float       not null,
    sensors_gyroscope0_y float       not null,
    sensors_gyroscope0_z float       not null,
    sensors_gyroscope1_x float       not null,
    sensors_gyroscope1_y float       not null,
    sensors_gyroscope1_z float       not null,
    sensors_sun_sensor_0 float       not null,
    sensors_sun_sensor_1 float       not null,
    energy_battery       float       not null,
    energy_solar         float       not null,
    computer_clock_speed bigint      not null,
    computer_memory_free bigint      not null,
    computer_memory_used bigint      not null
);