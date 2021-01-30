package com.hubtwork.cdn.api.service

import com.hubtwork.cdn.api.domain.Version
import com.hubtwork.cdn.api.exception.NotFoundException
import com.hubtwork.cdn.api.exception.ResponseException
import com.hubtwork.cdn.api.repository.VersionRepository
import org.jetbrains.annotations.TestOnly
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VersionService(val ddVersionRepository: VersionRepository) {

    fun versionCheck(version: Version) {
        if (read(1) == null) {

        }
    }

    @Transactional
    fun create(version: Version) {
        ddVersionRepository.save(version)
    }

    @Transactional(readOnly = true)
    @Throws(ResponseException::class)
    fun read(id: Int): Version {
        return ddVersionRepository.findByIdOrNull(id) ?: throw NotFoundException.DDVersion.get()
    }

    fun update() {

    }

    fun delete() {

    }
}