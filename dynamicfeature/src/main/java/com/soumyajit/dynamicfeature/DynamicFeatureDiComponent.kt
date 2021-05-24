package com.soumyajit.dynamicfeature

import com.soumyajit.core.NetworkClient
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DynamicFeatureModule::class
    ]
)
interface DynamicFeatureDiComponent : DynamicFeatureDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun networkClient(networkClient: NetworkClient): Builder

        fun build(): DynamicFeatureDiComponent
    }
}