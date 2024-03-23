package com.example.moviesapptask.common.initializers

import android.content.Context
import androidx.startup.Initializer
import com.example.moviesapptask.common.koin.appModule
import com.example.moviesapptask.common.koin.dataSourceModule
import com.example.moviesapptask.common.koin.repositoryModule
import com.example.moviesapptask.common.koin.useCaseModule
import com.example.moviesapptask.common.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context)
            modules(
                appModule,
                viewModelModule,
                repositoryModule,
                dataSourceModule,
                useCaseModule,
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}