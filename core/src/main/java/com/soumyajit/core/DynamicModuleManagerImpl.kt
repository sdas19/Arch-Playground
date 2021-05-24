package com.soumyajit.core

import android.util.Log
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import javax.inject.Inject

class DynamicModuleManagerImpl @Inject constructor(
    private val splitInstallManager: SplitInstallManager,
) : DynamicModuleManager {

    private val TAG = DynamicModuleManagerImpl::class.java.simpleName

    override fun isModuleAvailable(moduleName: String): Boolean {
        return splitInstallManager.installedModules.contains(moduleName)
    }

    override fun installModule(
        moduleName: String,
        listener: SplitInstallStateUpdatedListener
    ) {
        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()
        splitInstallManager.apply {
            registerListener(listener)
            startInstall(request).addOnCompleteListener {
                unregisterListener(listener)
            }
        }
    }

    override fun installDeferred(moduleNames: List<String>) {
        splitInstallManager.deferredInstall(moduleNames).addOnSuccessListener {
            Log.e(TAG, "Deferred installation of $moduleNames")
        }.addOnFailureListener {
            Log.e(TAG, "Failed installation of $moduleNames")
        }
    }
}