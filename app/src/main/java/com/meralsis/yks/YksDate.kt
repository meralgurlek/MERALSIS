package com.meralsis.yks

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

object YksDate {
    // Update this constant when ÖSYM publishes the official exam date for the target year.
    private val target = LocalDateTime.of(2027, 6, 19, 10, 0)
    fun remaining(): Duration = Duration.between(LocalDateTime.now(ZoneId.systemDefault()), target).coerceAtLeast(Duration.ZERO)
}
