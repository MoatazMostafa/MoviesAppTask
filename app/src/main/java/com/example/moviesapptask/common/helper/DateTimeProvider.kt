package com.example.moviesapptask.common.helper

import java.time.Instant
import java.time.LocalDateTime

interface DateTimeProvider {
    fun getTimeNowInMillis(): Long

    fun getCurrentInstant(): Instant
    fun getTimeNow(): LocalDateTime
}


class DateTimeProviderImpl: DateTimeProvider {
    override fun getTimeNowInMillis(): Long {
        return System.currentTimeMillis()
    }

    override fun getCurrentInstant(): Instant {
        return Instant.now()
    }

    override fun getTimeNow(): LocalDateTime {
        return LocalDateTime.now()
    }
}