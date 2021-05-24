package com.soumyajit.core

import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener

interface DynamicModuleManager {
    fun isModuleAvailable(moduleName: String): Boolean
    fun installModule(
        moduleName: String,
        listener: SplitInstallStateUpdatedListener
    )
    fun installDeferred(
        moduleNames: List<String>
    )
}