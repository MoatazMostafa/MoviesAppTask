package com.example.moviesapptask.data.cache.base

interface CacheEntryFactory<K, T>   {
    fun createCacheEntry(key: K, data: T, vararg additionalInfo: Any?) : CacheEntry<K, T>
}