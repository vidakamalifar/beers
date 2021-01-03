package com.fob.beers

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fob.beers.dao.BeerDAO
import com.fob.beers.databease.AppDatabase
import com.fob.beers.model.Beer
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.fob.beers", appContext.packageName)
    }


    @RunWith(AndroidJUnit4::class)
    class SimpleEntityReadWriteTest {
        private lateinit var mUserDao: BeerDAO
        private lateinit var mDb: AppDatabase

        @Before
        fun createDb() {
            val context = ApplicationProvider.getApplicationContext<Context>()
            mDb = Room.inMemoryDatabaseBuilder(
                context, AppDatabase::class.java
            ).build()
            mUserDao = mDb.beerDAO!!

        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            mDb.close()
        }

        @Test
        @Throws(Exception::class)
        fun writeUserAndReadInList() {
            val beerList: ArrayList<Beer?> = ArrayList()
            for (i in 0..3) {
                val beer = Beer("vida$i")
                beerList.add(beer)
            }
            mUserDao.insert(beerList)
            val byName = mUserDao.getBeers()
            assertThat("Db Test Result", byName?.get(0)?.name, equalTo("vida0"))
        }
    }
}