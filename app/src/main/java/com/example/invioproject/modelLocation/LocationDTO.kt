package com.example.invioproject.modelLocation

import com.example.invioproject.modelPost.Info
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LocationDTO(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    var results: List<ResultLocation>
): Serializable