package com.example.moviesapptask.data.cache.policy

import com.example.moviesapptask.common.helper.DateTimeProvider
import com.example.moviesapptask.data.cache.base.CacheAccessor
import com.example.moviesapptask.data.cache.base.CacheEntry
import com.example.moviesapptask.data.cache.base.CacheRetentionPolicy
import com.example.moviesapptask.data.cache.entry.TimedCacheEntry
import java.time.Duration

class TimedCacheRetentionPolicy<K, T>(private val timeProvider: DateTimeProvider) :
    CacheRetentionPolicy<K, T> {
    override fun isCacheEntryValid(entry: CacheEntry<K, T>): Boolean {
        if(entry is TimedCacheEntry)    {
            if(entry.timeToLive == Duration.ZERO) return true

            val now = timeProvider.getCurrentInstant()
            return Duration.between(entry.updateTime, now) < entry.timeToLive
        }
        return false
    }

    override fun cacheEntryRead(entry: CacheEntry<K, T>, cacheAccessor: CacheAccessor<K, T>?) {
        // Empty implementation
    }

    override fun cacheEntryCreated(entry: CacheEntry<K, T>, cacheAccessor: CacheAccessor<K, T>?) {
        // Empty implementation
    }
}