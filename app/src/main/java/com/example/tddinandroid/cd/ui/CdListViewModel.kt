package com.example.tddinandroid.cd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.Serializable
import javax.inject.Inject

data class Cd(val albumName: String, val artistName: String) : Serializable

@HiltViewModel
class CdListViewModel @Inject constructor() : ViewModel(), Counter {
    fun loadCds(): List<Cd> {
        return listOf(
            Cd(albumName = "Dissolution", artistName = "The Overmind"),
            Cd(albumName = "Wishes and Delusions", artistName = "Rakoon"),
            Cd(albumName = "Our Smiles", artistName = "Rakoon")
        )
    }

    val cdList: LiveData<List<Cd>> = liveData {
        emit(loadCds())
    }

    override fun getCount() = cdList.value?.size ?: 0
}