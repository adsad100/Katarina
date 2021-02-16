package com.hubtwork.jpa.domain

import java.io.Serializable

class ComposableKey(
    val accountId: String = "",
    val matchId: Long = 0
): Serializable