package com.example.tddinandroid.cd.infrastructure

import com.example.tddinandroid.cd.domain.Cd
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class InMemoryCdRepositoryTest : EqualsTestContract<CdRepository> {

    private val cdOne = Cd("album", "artist")
    private val cdTwo = Cd("albumTwo", "artistTwo")

    private val mapContentsOne = mutableMapOf(0 to cdOne)

    override val objX: CdRepository = InMemoryCdRepository(mapContentsOne)
    override val objY: CdRepository = InMemoryCdRepository(HashMap(mapContentsOne))
    override val objZ: CdRepository = InMemoryCdRepository(mapContentsOne.toMutableMap())


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
        assertTrue("The value should be a NotAvailable type!", actual is ReadResult.NotAvailable)
    }
}