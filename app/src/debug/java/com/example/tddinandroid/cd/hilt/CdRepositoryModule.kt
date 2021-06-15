package com.example.tddinandroid.cd.hilt

import com.example.tddinandroid.cd.infrastructure.CdRepository
import com.example.tddinandroid.cd.infrastructure.InMemoryCdRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CdRepositoryModule {

    @Binds
    abstract fun bindCdRepository(repository: InMemoryCdRepository): CdRepository
}