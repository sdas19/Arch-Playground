package com.soumyajit.core

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkClientModule::class,
        NetworkModule::class,
        DynamicModuleDiModule::class,
        DynamicModuleManagerDiModule::class
    ]
)
interface CoreDiComponent {

    val networkClient: NetworkClient

    val dynamicModuleManager: DynamicModuleManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreDiComponent
    }
}