package com.example.invioproject.Util

import com.example.invioproject.modelPost.Result
import com.example.invioproject.modelCharacter.CharacterDTO
import com.example.invioproject.modelLocation.ResultLocation


sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<Result>) : ApiState()
    class CharacterSucess(val data:CharacterDTO) : ApiState()
    class LocationSucess(val data: List<ResultLocation>) : ApiState()
    object Empty : ApiState()
}
