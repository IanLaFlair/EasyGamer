package com.ian.easygamer

import android.app.Application
import com.ian.core.di.databaseModule
import com.ian.core.di.networkModule
import com.ian.core.di.repositoryModule
import com.ian.easygamer.di.useCaseModule
import com.ian.easygamer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EasyGamerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@EasyGamerApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}