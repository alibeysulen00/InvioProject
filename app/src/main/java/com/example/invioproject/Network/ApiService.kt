package com.example.invioproject.Network

import com.example.invioproject.modelPost.PostDTO
import com.example.invioproject.modelCharacter.CharacterDTO
import com.example.invioproject.modelLocation.LocationDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface ApiService {

    @GET("character")
    suspend fun getPost(
        @Query("page") id: String
    ): PostDTO


    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: String
    ): CharacterDTO

    @GET("location")
    suspend fun getLocation(
        @Path("id") id: String
    ): LocationDTO
}