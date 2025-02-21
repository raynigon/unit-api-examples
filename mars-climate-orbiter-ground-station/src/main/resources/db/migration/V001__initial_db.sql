CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE
    TABLE
        telemetry_record(
            id uuid NOT NULL,
            recorded timestamptz NOT NULL,
            rtt BIGINT NOT NULL,
            fuel_nto FLOAT NOT NULL,
            fuel_hydrazine FLOAT NOT NULL,
            sensors_gyroscope0_x FLOAT NOT NULL,
            sensors_gyroscope0_y FLOAT NOT NULL,
            sensors_gyroscope0_z FLOAT NOT NULL,
            sensors_gyroscope1_x FLOAT NOT NULL,
            sensors_gyroscope1_y FLOAT NOT NULL,
            sensors_gyroscope1_z FLOAT NOT NULL,
            sensors_sun_sensor_0 FLOAT NOT NULL,
            sensors_sun_sensor_1 FLOAT NOT NULL,
            energy_battery FLOAT NOT NULL,
            energy_solar FLOAT NOT NULL,
            computer_clock_speed BIGINT NOT NULL,
            computer_memory_free BIGINT NOT NULL,
            computer_memory_used BIGINT NOT NULL
        );