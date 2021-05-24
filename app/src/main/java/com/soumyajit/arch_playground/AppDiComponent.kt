package com.soumyajit.arch_playground

import com.soumyajit.core.DynamicModuleManager
import dagger.BindsInstance
import dagger.Component

@Component
interface AppDiComponent : AppDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun dynamicModuleManager(dynamicModuleManager: DynamicModuleManager): Builder

        fun build(): AppDiComponent
    }
}