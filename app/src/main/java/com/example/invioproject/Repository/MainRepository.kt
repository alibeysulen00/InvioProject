package com.example.invioproject.Repository

import com.example.invioproject.Network.ApiServiceImpl
import com.example.invioproject.modelPost.PostDTO
import com.example.invioproject.modelCharacter.CharacterDTO
import com.example.invioproject.modelLocation.LocationDTO
import com.example.invioproject.modelPost.Info
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MainRepository
@Inject
constructor( val apiServiceImpl: ApiServiceImpl) {

    fun getPost(page:String): Flow<PostDTO> = flow {
        emit(apiServiceImpl.getPost(page))
    }.flowOn(Dispatchers.IO)

    fun getCharacter(id:String): Flow<CharacterDTO> = flow {
        emit(apiServiceImpl.getCharacter(id))
    }.flowOn(Dispatchers.IO)

    fun getLocation(id:String): Flow<LocationDTO> = flow {
        emit(apiServiceImpl.getLocation(id))
    }.flowOn(Dispatchers.IO)

}