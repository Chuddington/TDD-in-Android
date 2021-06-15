package com.example.tddinandroid.cd.infrastructure

import com.example.tddinandroid.cd.domain.Cd
import javax.inject.Inject

class InMemoryCdRepository @Inject constructor() : CdRepository {
    private val map: MutableMap<Int, Cd> = mutableMapOf(
        0 to Cd(albumName = "Dissolution", artistName = "The Overmind"),
        1 to Cd(albumName = "Wishes and Delusions", artistName = "Rakoon"),
        2 to Cd(albumName = "Our Smiles", artistName = "Rakoon")
    )

    override fun getAll(): List<Cd> = map.values.toList()
}