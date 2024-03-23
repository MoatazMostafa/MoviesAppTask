package com.example.moviesapptask.data.cache.base

interface CacheRetentionPolicy<K, T> {
    fun isCacheEntryValid(entry: CacheEntry<K, T>): Boolean
    fun cacheEntryRead(entry: CacheEntry<K, T>, cacheAccessor: CacheAccessor<K, T>? = null)
    fun cacheEntryCreated(entry: CacheEntry<K, T>, cacheAccessor: CacheAccessor<K, T>? = null)
}

