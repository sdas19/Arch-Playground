package com.soumyajit.arch_playground

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.soumyajit.core.DynamicModuleManager
import com.soumyajit.staticfeature.StaticFeatureEntryActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dynamicModuleManager: DynamicModuleManager
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AppDepsProvider).appDeps().inject(this)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            checkAndOpenDynamicModuleActivity()
        }
        button_2.setOnClickListener {
            startActivity(Intent(this, StaticFeatureEntryActivity::class.java))
        }
    }

    private fun checkAndOpenDynamicModuleActivity() {
        val moduleName = getString(R.string.module_dynamic_feature)
        if (dynamicModuleManager.isModuleAvailable(moduleName)) {
            onSuccessfulLoad()
        } else {
            val listener = SplitInstallStateUpdatedListener { state ->
                val multiInstall = state.moduleNames().size > 1
                val names = state.moduleNames().joinToString(" - ")
                when (state.status()) {
                    SplitInstallSessionStatus.DOWNLOADING -> {
                        displayLoadingState(state, "Downloading $names")
                    }
                    SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                        /*
                          This may occur when attempting to download a sufficiently large module.

                          In order to see this, the application has to be uploaded to the Play Store.
                          Then features can be requested until the confirmation path is triggered.
                         */
                        startIntentSender(state.resolutionIntent()?.intentSender, null, 0, 0, 0)
                    }
                    SplitInstallSessionStatus.INSTALLED -> {
                        Log.v(TAG, "Install completed multiInstall value -> $multiInstall")
                        onSuccessfulLoad(launch = !multiInstall)
                    }

                    SplitInstallSessionStatus.INSTALLING -> displayLoadingState(
                        state,
                        "Installing $names"
                    )
                    SplitInstallSessionStatus.FAILED -> {
                        Log.v(TAG, "Error: ${state.errorCode()} for module ${state.moduleNames()}")
                        dynamicModuleManager.installDeferred(listOf(moduleName))
                    }
                }
            }
            dynamicModuleManager.installModule(moduleName = moduleName, listener = listener)
        }
    }

    /** Display a loading state to the user. */
    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        Log.v(TAG, "displayLoadingState total bytes to download -> ${state.totalBytesToDownload()}")
        Log.v(TAG, "displayLoadingState bytes downloaded -> ${state.bytesDownloaded()}")
        progress_bar.max = state.totalBytesToDownload().toInt()
        progress_bar.progress = state.bytesDownloaded().toInt()

        updateProgressMessage(message)
    }

    private fun updateProgressMessage(message: String) {
        progress_text.text = message
    }

    private fun onSuccessfulLoad(launch: Boolean = true) {
        Log.v(TAG, "onSuccessfulLoad launch -> $launch")
        if (launch) {
            val intent = Intent().apply {
                setClassName(
                    BuildConfig.APPLICATION_ID,
                    "com.soumyajit.arch_playground.dynamicfeaturemodule.DynamicFeatureModuleEntryActivity"
                )
            }
            startActivity(intent)
        }
    }
}