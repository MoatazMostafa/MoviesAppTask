package com.example.moviesapptask.data.cache.cache

import com.example.moviesapptask.data.cache.base.Cache
import com.example.moviesapptask.data.cache.base.CacheAccessor
import com.example.moviesapptask.data.cache.base.CacheEntryFactory
import com.example.moviesapptask.data.cache.base.CacheRetentionPolicy
import com.example.moviesapptask.data.cache.base.CacheStore

class CacheImpl<K, T> internal constructor(
    private val cacheStore: CacheStore<K, T>,
    private val cacheEntryFactory: CacheEntryFactory<K, T>,
    private val cacheAccessor: CacheAccessor<K, T>,
    private val cacheRetentionPolicy: CacheRetentionPolicy<K, T>
) : Cache<K, T> {
    override suspend fun hasEntry(key: K): Boolean {
        cacheStore.getEntry(key)?.let { entry ->
            if (cacheRetentionPolicy.isCacheEntryValid(entry)) {
                cacheRetentionPolicy.cacheEntryRead(entry, cacheAccessor)
                return true
            } else {
                cacheStore.removeEntry(key)
                return false
            }
        } ?: return false
    }

    override suspend fun getEntry(key: K): T? {
        cacheStore.getEntry(key)?.let { entry ->
            if (cacheRetentionPolicy.isCacheEntryValid(entry)) {
                cacheRetentionPolicy.cacheEntryRead(entry, cacheAccessor)
                return entry.data
            } else {
                cacheStore.removeEntry(key)
                return null
            }
        } ?: return null
    }

    override suspend fun putEntry(key: K, data: T): Boolean {
        val entry = cacheEntryFactory.createCacheEntry(key = key, data = data)
        cacheRetentionPolicy.cacheEntryCreated(entry)
        return cacheStore.putEntry(entry)
    }

    override suspend fun removeEntry(key: K): T? {
        return cacheStore.removeEntry(key)?.data
    }

    override suspend fun removeAll() {
        cacheStore.removeAll()
    }
}

class CacheBuilder<K, T> {
    private lateinit var cacheEntryFactory: CacheEntryFactory<K, T>
    private lateinit var cacheStore: CacheStore<K, T>
    private lateinit var cacheRetentionPolicy: CacheRetentionPolicy<K, T>
    private lateinit var cacheAccessor: CacheAccessor<K, T>

    fun setCacheEntryFactory(cacheEntryFactory: CacheEntryFactory<K, T>): CacheBuilder<K, T> {
        this.cacheEntryFactory = cacheEntryFactory
        return this
    }

    fun setCacheStore(cacheStore: CacheStore<K, T>): CacheBuilder<K, T> {
        this.cacheStore = cacheStore
        return this
    }

    fun setCachePolicy(cacheRetentionPolicy: CacheRetentionPolicy<K, T>): CacheBuilder<K, T> {
        this.cacheRetentionPolicy = cacheRetentionPolicy
        return this
    }

    fun setCacheAccessor(cacheAccessor: CacheAccessor<K, T>): CacheBuilder<K, T> {
        this.cacheAccessor = cacheAccessor
        return this
    }

    fun build(): Cache<K, T> {
        if (::cacheEntryFactory.isInitialized.not()) {
            throw IllegalStateException("A ${CacheEntryFactory::class.simpleName} instance should be provided")
        }
        if (::cacheStore.isInitialized.not()) {
            throw IllegalStateException("A ${CacheStore::class.simpleName} instance should be provided")
        }
        if (::cacheRetentionPolicy.isInitialized.not()) {
            throw IllegalStateException("A ${CacheRetentionPolicy::class.simpleName} instance should be provided")
        }
        if (::cacheAccessor.isInitialized.not()) {
            cacheAccessor = cacheStore.getCacheAccessor()
        }

        return CacheImpl<K, T>(
            cacheEntryFactory = cacheEntryFactory,
            cacheStore = cacheStore,
            cacheRetentionPolicy = cacheRetentionPolicy,
            cacheAccessor = cacheAccessor
        )
    }
}