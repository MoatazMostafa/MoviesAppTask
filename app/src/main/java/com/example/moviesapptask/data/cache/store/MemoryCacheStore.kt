package com.example.moviesapptask.data.cache.store

import com.example.moviesapptask.data.cache.base.CacheAccessor
import com.example.moviesapptask.data.cache.base.CacheEntry
import com.example.moviesapptask.data.cache.base.CacheStore
import java.util.Collections

class MemoryCacheStore<K, T> : CacheStore<K, T> {

    private val entries = Collections.synchronizedMap(HashMap<K, CacheEntry<K, T>>())
    override suspend fun hasEntry(key: K): Boolean {
        return entries.containsKey(key)
    }

    override suspend fun putEntry(cacheEntry: CacheEntry<K, T>): Boolean {
        val previousEntry = entries.put(cacheEntry.key, cacheEntry)
        return previousEntry != null
    }

    override suspend fun getEntry(key: K): CacheEntry<K, T>? {
        return entries[key]
    }

    override suspend fun removeEntry(key: K): CacheEntry<K, T>? {
        return entries.remove(key)
    }

    override suspend fun removeAll(): Boolean {
        val hadValues = entries.isNotEmpty()
        entries.clear()
        return hadValues
    }

    override fun getCacheAccessor(): CacheAccessor<K, T> {
        return MemoryCacheStoreAccessor()
    }

    inner class MemoryCacheStoreAccessor : CacheAccessor<K, T> {
        override suspend fun getCacheEntry(key: K): CacheEntry<K, T>? {
            return getEntry(key)
        }

        override suspend fun removeCacheEntry(key: K): CacheEntry<K, T>? {
            return removeEntry(key)
        }

        override suspend fun getAllCacheEntries(): List<CacheEntry<K, T>> {
            return entries.values.toList()
        }

    }
}

