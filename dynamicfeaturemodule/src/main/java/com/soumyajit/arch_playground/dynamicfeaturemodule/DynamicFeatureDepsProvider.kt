package com.soumyajit.arch_playground.dynamicfeaturemodule

import com.soumyajit.arch_playground.dynamicfeaturemodule.DynamicFeatureDeps

interface DynamicFeatureDepsProvider {
    fun dynamicFeatureDeps(): DynamicFeatureDeps
}