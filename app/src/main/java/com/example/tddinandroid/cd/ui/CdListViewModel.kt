package com.example.tddinandroid.cd.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CdListViewModel @Inject constructor() : ViewModel(), Counter {
    override fun getCount() = 3
}