package com.example.tddinandroid.cd.infrastructure

import com.example.tddinandroid.cd.domain.Cd
import javax.inject.Inject

class RestCdRepository @Inject constructor() : CdRepository {

    override fun getAll(): List<Cd> = listOf()
}