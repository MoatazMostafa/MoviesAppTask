package com.example.moviesapptask.data.cache.entry

import com.example.moviesapptask.data.cache.base.CacheEntry
import java.time.Duration
import java.time.Instant

class TimedCacheEntry<K, T>(key: K, data: T, val updateTime: Instant, val timeToLive: Duration) : CacheEntry<K, T>(key, data)