package com.example.tddinandroid.cd.infrastructure

import com.example.tddinandroid.cd.domain.Cd
import javax.inject.Inject

data class InMemoryCdRepository @Inject constructor(
    private val map: MutableMap<Int, Cd>
) : CdRepository {
    override fun getAll(): List<Cd> = map.values.toList()

    override fun create(data: Cd): Int {
        val key = (map.keys.maxOrNull() ?: 0) + 1
        map[key] = data
        return key
    }

    override fun read(id: Int): ReadResult<Cd> = map[id]?.let { cd ->
        ReadResult.Success(cd)
    } ?: ReadResult.NotAvailable

}