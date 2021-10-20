package com.cake.cakeapp

import com.cake.cakeapp.modules.cakelist.CakeListViewModel
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest: TestCase() {

 //   private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}