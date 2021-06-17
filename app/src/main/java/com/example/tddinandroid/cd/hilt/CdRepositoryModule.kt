package com.example.tddinandroid.cd.hilt

import com.example.tddinandroid.cd.domain.Cd
import com.example.tddinandroid.cd.infrastructure.CdRepository
import com.example.tddinandroid.cd.infrastructure.InMemoryCdRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CdRepositoryModule {

    @Provides
    fun providesCdRepository(
        @CdRepositoryMap inMemoryMap: MutableMap<Int, Cd>,
        @PurchasedCdSet inMemoryPurchasedCdSet: MutableSet<Int>
    ): CdRepository =
        InMemoryCdRepository(inMemoryMap, inMemoryPurchasedCdSet)

}