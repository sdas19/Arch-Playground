package com.soumyajit.core

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
    interface DynamicModuleManagerDiModule {
        @Binds
        @Singleton
        abstract fun bindDynamicModule(dynamicModuleImpl: DynamicModuleManagerImpl): DynamicModuleManager
    }