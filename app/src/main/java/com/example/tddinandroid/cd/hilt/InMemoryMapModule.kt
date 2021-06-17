package com.example.tddinandroid.cd.hilt

import com.example.tddinandroid.cd.domain.Cd
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CdRepositoryMap

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PurchasedCdSet

@Module
@InstallIn(SingletonComponent::class)
object InMemoryMapModule {

    @CdRepositoryMap
    @Provides
    @Singleton
    fun providesInMemoryMap(): MutableMap<Int, Cd> {
        return mutableMapOf(
            0 to Cd(0, albumName = "Dissolution", artistName = "The Overmind"),
            1 to Cd(1, albumName = "Wishes and Delusions", artistName = "Rakoon"),
            2 to Cd(2, albumName = "Our Smiles", artistName = "Rakoon")
        )
    }

    @Provides
    @PurchasedCdSet
    @Singleton
    fun providePurchasedCdSet(): MutableSet<Int> = mutableSetOf()
}