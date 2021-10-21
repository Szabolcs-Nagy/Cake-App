package com.cake.cakeapp.modules.cakelist

/**
 * The event sealed class wit the
 * possible events that we can have
 */
sealed class CakeEvent {
    class Success(val cakes: List<Cake>) : CakeEvent()
    class Error(val message: String) : CakeEvent()
    object Loading : CakeEvent()
    object Empty : CakeEvent()
}