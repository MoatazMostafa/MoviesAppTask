package com.example.moviesapptask.data.cache.base

interface CacheStore<K, T> {
    suspend fun hasEntry(key: K): Boolean
    suspend fun putEntry(cacheEntry: CacheEntry<K, T>): Boolean
    suspend fun getEntry(key: K): CacheEntry<K, T>?
    suspend fun removeEntry(key: K): CacheEntry<K, T>?
    suspend fun removeAll(): Boolean

    fun getCacheAccessor(): CacheAccessor<K, T>
}

interface CacheAccessor<K, T>   {
    suspend fun getCacheEntry(key: K): CacheEntry<K, T>?
    suspend fun removeCacheEntry(key: K): CacheEntry<K, T>?
    suspend fun getAllCacheEntries(): List<CacheEntry<K, T>>
}