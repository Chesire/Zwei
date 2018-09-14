package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.Gender
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Assert
import org.junit.Test

class GenderAdapterTests {
    private val moshi = Moshi.Builder()
        .add(GenderAdapter())
        .build()
    private val moshiAdapter = moshi.adapter(Gender::class.java)

    @Test
    fun `Moshi converts 'Male' to '1'`() {
        Assert.assertEquals("1", moshiAdapter.toJson(Gender.Male))
    }

    @Test
    fun `Moshi converts 'Female' to '2'`() {
        Assert.assertEquals("2", moshiAdapter.toJson(Gender.Female))
    }

    @Test
    fun `Moshi converts '1' to 'Male'`() {
        Assert.assertEquals(Gender.Male, moshiAdapter.fromJson("1"))
    }

    @Test
    fun `Moshi converts '2' to 'Female'`() {
        Assert.assertEquals(Gender.Female, moshiAdapter.fromJson("2"))
    }

    @Test(expected = JsonDataException::class)
    fun `'0' produces JsonDataException`() {
        moshiAdapter.fromJson("0")
    }

    @Test(expected = JsonDataException::class)
    fun `'3' produces JsonDataException`() {
        moshiAdapter.fromJson("3")
    }
}