package com.cake.cakeapp.modules.cakelist

import com.google.gson.annotations.SerializedName

/**
 * The data class with the name of the values that we wat to get
 */
data class Cake(
    @SerializedName("title") val title : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("image") val image : String
)
