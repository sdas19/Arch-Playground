package com.soumyajit.arch_playground

import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.soumyajit.core.CoreComponentProvider
import com.soumyajit.core.CoreDiComponent
import com.soumyajit.core.DaggerCoreDiComponent
import com.soumyajit.core.NetworkClient
import com.soumyajit.staticfeature.DaggerStaticFeatureDiComponent
import com.soumyajit.staticfeature.StaticFeatureDeps
import com.soumyajit.staticfeature.StaticFeatureDiComponent

class ArchPlaygroundApp : SplitCompatApplication(), CoreComponentProvider, DepsProvider {

    private val coreDiComponent by lazy {
        initializeCoreComponent()
    }

    private val appComponent by lazy {
        initializeAppComponent()
    }

    private val staticFeatureDiComponent by lazy {
        initializeStaticFeatureComponent()
    }

    private fun initializeAppComponent(): AppDiComponent {
        return DaggerAppDiComponent
            .builder()
            .dynamicModuleManager(coreDiComponent.dynamicModuleManager)
            .build()
    }

    private fun initializeStaticFeatureComponent(): StaticFeatureDiComponent {
        return DaggerStaticFeatureDiComponent
            .builder()
            .networkClient(networkClient = networkClient)
            .build()
    }

    private fun initializeCoreComponent(): CoreDiComponent {
        return DaggerCoreDiComponent
            .builder()
            .application(this)
            .build()
    }

    override fun appDeps(): AppDeps {
        return appComponent
    }

    override fun staticFeatureDeps(): StaticFeatureDeps {
        return staticFeatureDiComponent
    }

    override val networkClient: NetworkClient
            by lazy { coreDiComponent.networkClient }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this);
    }
}