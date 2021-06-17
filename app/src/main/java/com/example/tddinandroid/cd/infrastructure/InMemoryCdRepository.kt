package com.example.tddinandroid.cd.infrastructure

import com.example.tddinandroid.cd.domain.Cd
import javax.inject.Inject

data class InMemoryCdRepository @Inject constructor(
    private val availableCds: MutableMap<Int, Cd>,
    private val purchasedCds: MutableSet<Int>
) : CdRepository {
    override fun getAll(): List<Cd> = availableCds.values.toList()

    override fun create(data: Cd): Int {
        val key = (availableCds.keys.maxOrNull() ?: 0) + 1
        availableCds[key] = data.copy(id = key)
        return key
    }

    override fun read(id: Int): ReadResult<Cd> = availableCds[id]?.let { cd ->
        ReadResult.Success(cd)
    } ?: ReadResult.NotAvailable

    override fun getPurchasedCds(userId: Int): Set<Int> = purchasedCds.toSet()

    override fun purchaseCd(id: Int) {
        if (availableCds.containsKey(id)) {
            purchasedCds.add(id)
        }
    }

    override fun hasPurchasedCd(id: Int): ReadResult<Boolean> =
        ReadResult.Success(purchasedCds.contains(id))

}