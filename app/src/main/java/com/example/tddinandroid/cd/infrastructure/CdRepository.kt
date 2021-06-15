package com.example.tddinandroid.cd.infrastructure

import com.example.tddinandroid.cd.domain.Cd

interface CdRepository {
    fun getAll(): List<Cd>
}