package com.raynigon.mco.groundstation.repo

import com.raynigon.mco.groundstation.model.TelemetryRecord
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TelemetryRecordRepository : JpaRepository<TelemetryRecord, UUID>
