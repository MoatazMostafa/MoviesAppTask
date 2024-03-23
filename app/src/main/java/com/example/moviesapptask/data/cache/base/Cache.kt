package com.example.moviesapptask.data.cache.base

interface Cache<K, T>   {

    suspend fun hasEntry(key: K): Boolean

    suspend fun getEntry(key: K): T?

    suspend fun putEntry(key: K, data: T): Boolean

    suspend fun removeEntry(key: K): T?

    suspend fun removeAll()

}