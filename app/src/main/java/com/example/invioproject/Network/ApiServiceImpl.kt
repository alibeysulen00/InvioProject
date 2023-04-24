package com.example.invioproject.Network

import com.example.invioproject.modelPost.PostDTO
import com.example.invioproject.modelCharacter.CharacterDTO
import com.example.invioproject.modelLocation.LocationDTO
import com.example.invioproject.modelPost.Info
import javax.inject.Inject



class ApiServiceImpl @Inject constructor(val apiService: ApiService) {

    suspend fun getPost(page:String): PostDTO = apiService.getPost(page)
    suspend fun getCharacter(id:String): CharacterDTO = apiService.getCharacter(id)
    suspend fun getLocation(id:String): LocationDTO = apiService.getLocation(id)

}