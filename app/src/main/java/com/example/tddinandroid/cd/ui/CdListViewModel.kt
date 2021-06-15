package com.example.tddinandroid.cd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.Serializable
import javax.inject.Inject

data class Cd(val albumName: String, val artistName: String) : Serializable

@HiltViewModel
class CdListViewModel @Inject constructor() : ViewModel(), Counter {
    private val _cdList: MutableLiveData<List<Cd>> = MutableLiveData<List<Cd>>().apply {
        value = loadCds()
    }

    fun loadCds(): List<Cd> {
        return listOf(
            Cd(albumName = "Dissolution", artistName = "The Overmind"),
            Cd(albumName = "Wishes and Delusions", artistName = "Rakoon"),
            Cd(albumName = "Our Smiles", artistName = "Rakoon")
        )
    }

    val cdList: LiveData<List<Cd>> = _cdList

    override fun getCount() = cdList.value?.size ?: 0
}