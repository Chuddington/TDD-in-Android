package com.example.tddinandroid.cd.hilt

import com.example.tddinandroid.BuildConfig
import com.example.tddinandroid.cd.infrastructure.CdRepository
import com.example.tddinandroid.cd.infrastructure.InMemoryCdRepository
import com.example.tddinandroid.cd.infrastructure.RestCdRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CdRepositoryModule {

    @Provides
    fun providesCdRepository(): CdRepository {
        return if (BuildConfig.DEBUG) {
            InMemoryCdRepository()
        } else {
            RestCdRepository()
        }
    }
}