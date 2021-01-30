package com.hubtwork.cdn.api.repository

import com.hubtwork.cdn.api.domain.Version
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VersionRepository: JpaRepository<Version, Int>