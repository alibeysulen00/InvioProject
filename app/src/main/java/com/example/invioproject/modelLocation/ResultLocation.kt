package com.example.invioproject.modelLocation


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultLocation(
    @SerializedName("created")
    val created: String,
    @SerializedName("residents")
    val residents: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
):Serializable