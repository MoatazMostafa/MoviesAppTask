package com.example.moviesapptask.data.cache.entryFactory

import com.example.moviesapptask.common.helper.DateTimeProvider
import com.example.moviesapptask.data.cache.base.CacheEntry
import com.example.moviesapptask.data.cache.base.CacheEntryFactory
import com.example.moviesapptask.data.cache.entry.TimedCacheEntry
import java.time.Duration

class TimeCacheEntryFactory<K, T>(private val dateTimeProvider: DateTimeProvider, private val ttl: Duration) :
    CacheEntryFactory<K, T> {

    override fun createCacheEntry(key: K, data: T, vararg additionalInfo: Any?): CacheEntry<K, T> {
        return TimedCacheEntry(key = key, data = data, updateTime = dateTimeProvider.getCurrentInstant(), timeToLive = ttl)
    }
}