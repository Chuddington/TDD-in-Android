package com.example.tddinandroid.cd.hilt

import com.example.tddinandroid.cd.domain.Cd
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CdRepositoryMap

@Module
@InstallIn(SingletonComponent::class)
object InMemoryMapModule {

    @CdRepositoryMap
    @Provides
    fun providesInMemoryMap(): MutableMap<Int, Cd> {
        return mutableMapOf(
            0 to Cd(albumName = "Dissolution", artistName = "The Overmind"),
            1 to Cd(albumName = "Wishes and Delusions", artistName = "Rakoon"),
            2 to Cd(albumName = "Our Smiles", artistName = "Rakoon")
        )
    }
}