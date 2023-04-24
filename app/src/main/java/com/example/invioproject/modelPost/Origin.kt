package com.example.invioproject.modelPost

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Origin(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
):Serializable