package com.soumyajit.dynamicfeature

import com.soumyajit.core.CoreComponentProvider

object Initialiser {

    @JvmStatic
    fun dynamicFeatureDaggerComponent(coreComponentProvider: CoreComponentProvider): DynamicFeatureDiComponent {
        return DaggerDynamicFeatureDiComponent.builder()
            .networkClient(coreComponentProvider.networkClient)
            .build()
    }
}