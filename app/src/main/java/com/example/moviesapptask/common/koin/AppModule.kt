package com.example.moviesapptask.common.koin

import com.example.moviesapptask.common.helper.DateTimeProvider
import com.example.moviesapptask.common.helper.DateTimeProviderImpl
import com.example.moviesapptask.common.network.configuration.ConfigurationService
import com.example.moviesapptask.common.network.configuration.ConfigurationServiceImpl
import org.koin.dsl.module

val appModule = module {
    single<DateTimeProvider> { DateTimeProviderImpl() }
    single<ConfigurationService> { ConfigurationServiceImpl(get()) }
}