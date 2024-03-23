package com.example.moviesapptask.data.cache

import com.example.moviesapptask.common.helper.DateTimeProvider
import com.example.moviesapptask.data.cache.base.Cache
import com.example.moviesapptask.data.cache.cache.CacheBuilder
import com.example.moviesapptask.data.cache.entryFactory.TimeCacheEntryFactory
import com.example.moviesapptask.data.cache.policy.TimedCacheRetentionPolicy
import com.example.moviesapptask.data.cache.store.MemoryCacheStore

import java.time.Duration

private val defaultTimeToLive = Duration.ofMinutes(5)
fun <K, T> timedMemoryCache(dateTimeProvider: DateTimeProvider, duration: Duration = defaultTimeToLive): Cache<K, T> {

    return CacheBuilder<K, T>()
        .setCacheStore(MemoryCacheStore())
        .setCachePolicy(TimedCacheRetentionPolicy(dateTimeProvider))
        .setCacheEntryFactory(TimeCacheEntryFactory(dateTimeProvider, duration))
        .build()
}