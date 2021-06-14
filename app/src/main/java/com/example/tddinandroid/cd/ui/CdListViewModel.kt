package com.example.tddinandroid.cd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CdListViewModel : ViewModel(), Counter {
    override fun getCount() = 3
}