package com.cake.cakeapp

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.cake.cakeapp.di.AppModule
import com.cake.cakeapp.modules.cakelist.CakeListViewModel
import com.cake.cakeapp.repository.CakeRepository
import com.cake.cakeapp.repository.CakeRepositoryImpl
import com.cake.cakeapp.service.ServiceHelper
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.*

/**
 * Testing the code
 */
class CakeUnitTest {

    private lateinit var viewModel: CakeListViewModel
    private lateinit var networkHelper: ServiceHelper
    private lateinit var cakeRepositoryImpl: CakeRepository

    @Before
    fun setup() {
        networkHelper = Mockito.mock(ServiceHelper::class.java)
        cakeRepositoryImpl = Mockito.mock(CakeRepository::class.java)
        viewModel = CakeListViewModel(cakeRepository = cakeRepositoryImpl)
    }

    /**
     * Testing the View Model
     */
    @Test
    fun viewModelNotNull() {
        assertNotNull(viewModel)
    }

    /**
     * Testing if api response is successful
     */
    @Test
    fun getCakesIsSuccessful() {
        suspend {
        Mockito.`when`(networkHelper.getCakes().isSuccessful).thenReturn(true)
        }

    }

    /**
     * Testing if response fails
     */
    @Test
    fun loadFails() {
        suspend {
        Mockito.`when`(networkHelper.failCakes().isSuccessful).thenReturn(false)
        }
    }


}