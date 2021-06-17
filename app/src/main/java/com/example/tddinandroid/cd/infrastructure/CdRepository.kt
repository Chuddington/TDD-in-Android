package com.example.tddinandroid.cd.infrastructure

import com.example.tddinandroid.cd.domain.Cd

interface CdRepository {
    fun getAll(): List<Cd>
    fun create(data: Cd): Int
    fun read(id: Int): ReadResult<Cd>
    fun getPurchasedCds(userId: Int): Set<Int>
    fun purchaseCd(id: Int)
    fun hasPurchasedCd(id: Int): ReadResult<Boolean>
}