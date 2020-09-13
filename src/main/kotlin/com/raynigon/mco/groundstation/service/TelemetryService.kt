package com.raynigon.mco.groundstation.service

import com.raynigon.mco.groundstation.model.TelemetryRecord
import com.raynigon.mco.groundstation.repo.TelemetryRecordRepository
import org.springframework.stereotype.Service

interface TelemetryService {

    fun record(record: TelemetryRecord)
}

@Service
class TelemetryServiceImpl(private val repository: TelemetryRecordRepository) : TelemetryService {

    override fun record(record: TelemetryRecord) {
        repository.save(record)
    }
}
