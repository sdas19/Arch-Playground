package com.soumyajit.staticfeature

import com.soumyajit.core.NetworkClient
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        StaticFeatureModule::class
    ]
)
interface StaticFeatureDiComponent : StaticFeatureDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun networkClient(networkClient: NetworkClient): Builder

        fun build(): StaticFeatureDiComponent
    }
}