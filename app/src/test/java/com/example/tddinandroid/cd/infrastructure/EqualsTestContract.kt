package com.example.tddinandroid.cd.infrastructure

import androidx.annotation.CallSuper
import org.junit.Assert
import org.junit.Test

interface EqualsTestContract<T : Any> {
    val objX: T
    val objY: T
    val objZ: T

    @CallSuper
    fun reflexiveEquals() {
        Assert.assertTrue("The object should equal itself!", objX == objX)
    }

    @CallSuper
    fun symmetricEquals() {
        Assert.assertTrue("X -> Y should be the same!", objX == objY)
        Assert.assertTrue("Y -> X should be the same!", objY == objX)
    }

    @CallSuper
    fun transitiveEquals() {
        Assert.assertTrue("X -> Y should be the same!", objX == objY)
        Assert.assertTrue("Y -> Z should be the same!", objY == objZ)
        Assert.assertTrue("X -> Z should be the same!", objX == objZ)
    }

    @CallSuper
    fun consistentEquals() {
        Assert.assertTrue("X -> Y should be the same!", objX == objY)
        Assert.assertTrue("X -> Y should be the same!", objX == objY)
        Assert.assertTrue("X -> Y should be the same!", objX == objY)

        updateObjectClone()

        Assert.assertFalse("X -> Y should be the same!", objX == objY)
    }

    fun updateObjectClone()

    @CallSuper
    fun nullReferencing() {
        Assert.assertFalse("Null objects should not be equal!", objX.equals(null))
    }
}