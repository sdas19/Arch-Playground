package com.soumyajit.core

import android.app.Application
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DynamicModuleDiModule {

    @Provides
    @Singleton
    fun provideSplitInstallManager(application: Application): SplitInstallManager {
        return SplitInstallManagerFactory.create(application.applicationContext)
    }
}