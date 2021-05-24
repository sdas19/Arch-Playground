package com.soumyajit.staticfeature

import com.soumyajit.core.NetworkClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class StaticFeatureModule {

    @Provides
    @Singleton
    fun provideRetrofit(networkClient: NetworkClient): Retrofit {
        return networkClient.retrofit
    }
}