package com.soumyajit.arch_playground.dynamicfeaturemodule

import com.soumyajit.core.CoreComponentProvider

object Initialiser {

    @JvmStatic
    fun dynamicFeatureDaggerComponent(coreComponentProvider: CoreComponentProvider): DynamicFeatureDiComponent {
        return DaggerDynamicFeatureDiComponent.builder()
            .networkClient(coreComponentProvider.networkClient)
            .build()
    }
}