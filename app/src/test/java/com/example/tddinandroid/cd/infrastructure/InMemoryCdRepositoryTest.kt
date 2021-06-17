package com.example.tddinandroid.cd.infrastructure

import com.example.tddinandroid.cd.domain.Cd
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class InMemoryCdRepositoryTest : EqualsTestContract<CdRepository> {

    private val cdOne = Cd(id = 0, albumName = "album", artistName = "artist")
    private val cdTwo = Cd(id = 1, albumName = "albumTwo", artistName = "artistTwo")

    private val mapContentsOne = mutableMapOf(0 to cdOne)

    // Equality testing
    override val objX: CdRepository = InMemoryCdRepository(mapContentsOne, mutableSetOf())
    override val objY: CdRepository = InMemoryCdRepository(HashMap(mapContentsOne), mutableSetOf())
    override val objZ: CdRepository = InMemoryCdRepository(
        mapContentsOne.toMutableMap(),
        mutableSetOf()
    )

    override fun updateObjectClone() {
        objY.create(cdTwo)
    }

    @Test
    override fun consistentEquals() {
        super.consistentEquals()
    }

    @Test
    override fun nullReferencing() {
        super.nullReferencing()
    }

    @Test
    override fun reflexiveEquals() {
        super.reflexiveEquals()
    }

    @Test
    override fun symmetricEquals() {
        super.symmetricEquals()
    }

    @Test
    override fun transitiveEquals() {
        super.transitiveEquals()
    }

    @Test
    fun `Inserting data updates the internal representation`() {
        val dataId = objX.create(cdTwo)

        (objX.read(dataId) as? ReadResult.Success<Cd>)?.let {
            assertEquals(
                "The requested object should be the same as what was inserted!",
                cdTwo,
                it.value
            )
        } ?: fail("The read operation was not successful!")
    }

    @Test
    fun `Reading unavailable data returns a NotAvailable sealed class`() {
        val actual = objX.read(Int.MAX_VALUE)
        assertTrue(
            "The value should be a NotAvailable type!",
            actual is ReadResult.NotAvailable
        )
    }

    @Test
    fun `Can check whether a CD has been purchased or not`() {
        objX.purchaseCd(cdOne.id)

        val purchasedResult = (objX.hasPurchasedCd(cdOne.id) as ReadResult.Success<Boolean>)

        assertEquals(
            "The user should already have this CD!",
            true,
            purchasedResult.value,
        )

        val nonPurchasedResult = (objX.hasPurchasedCd(Int.MAX_VALUE) as ReadResult.Success<Boolean>)

        assertEquals(
            "The user should not have purchased this CD already!",
            false,
            nonPurchasedResult.value
        )
    }

    @Test
    fun `Cannot purchase a CD that is unavailable`() {
        objX.purchaseCd(Int.MAX_VALUE)

        val nonPurchasedResult = (objX.hasPurchasedCd(Int.MAX_VALUE) as ReadResult.Success<Boolean>)

        assertEquals(
            "The user should not have purchased this CD already!",
            false,
            nonPurchasedResult.value
        )
    }
}