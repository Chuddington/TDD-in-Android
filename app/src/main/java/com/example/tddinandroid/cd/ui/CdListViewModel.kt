package com.example.tddinandroid.cd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tddinandroid.cd.domain.Cd
import com.example.tddinandroid.cd.domain.Counter
import com.example.tddinandroid.cd.infrastructure.CdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CdListViewModel @Inject constructor(
    private val cdRepository: CdRepository
) : ViewModel(), Counter {

    val cdList: LiveData<List<Cd>> = liveData {
        emit(cdRepository.getAll())
    }

    override fun getCount() = cdList.value?.size ?: 0
}