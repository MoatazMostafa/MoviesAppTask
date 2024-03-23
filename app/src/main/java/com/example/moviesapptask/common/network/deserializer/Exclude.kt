package com.example.moviesapptask.common.network.deserializer

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

@Retention
annotation class Exclude()

object AnnotationExclusionStrategy: ExclusionStrategy {
    override fun shouldSkipField(fieldAttributes: FieldAttributes?): Boolean {
        return fieldAttributes?.getAnnotation(Exclude::class.java) != null
    }

    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        return false
    }
}