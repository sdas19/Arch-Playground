package com.soumyajit.core

import dagger.Binds
import dagger.Module

@Module
abstract class NetworkClientModule {
    @Binds
    abstract fun bindNetworkClient(networkClientImplementation: NetworkClientImpl): NetworkClient
}