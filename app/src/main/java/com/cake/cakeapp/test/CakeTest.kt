package com.cake.cakeapp.test

import com.cake.cakeapp.di.AppModule
import com.cake.cakeapp.service.ServiceHelperImpl
import org.mockito.Mockito
import org.junit.Before
import org.junit.Test

private val WRONG_BASE_URL = ""
class CakeTest {

    private lateinit var serviceHelperImpl: ServiceHelperImpl


    @Before
    fun setup() {
        serviceHelperImpl = Mockito.mock(ServiceHelperImpl::class.java)
    }

//    @Test
//    suspend fun cakeNotFound () {
//        Mockito.when(serviceHelperImpl.getCakes(Mockito.)){
//        }

}