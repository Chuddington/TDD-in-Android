package com.example.tddinandroid.cd.details.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.tddinandroid.cd.infrastructure.CdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CdDetailsViewModel @Inject constructor(private val cdRepository: CdRepository) : ViewModel() {
    private val userId = -1

    private val purchasedCds: MutableLiveData<Set<Int>> = MutableLiveData<Set<Int>>().also {
        it.value = cdRepository.getPurchasedCds(userId)
    }

    fun purchaseCd(cdId: Int) {
        viewModelScope.launch {
            cdRepository.purchaseCd(cdId)
            purchasedCds.postValue(cdRepository.getPurchasedCds(userId))
        }
    }
}