package com.soumyajit.arch_playground.dynamicfeaturemodule

import com.soumyajit.core.NetworkClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DynamicFeatureModule {

    @Provides
    @Singleton
    fun provideRetrofit(networkClient: NetworkClient): Retrofit {
        return networkClient.retrofit
    }
}