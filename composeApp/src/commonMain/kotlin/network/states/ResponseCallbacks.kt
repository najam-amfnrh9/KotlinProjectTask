package network.states

import models.ApiResponse

sealed class ResponseCallbacks {
        data object Loading: ResponseCallbacks()
        data class Error(val errorMessage: String):ResponseCallbacks()
        data class Success(val responseData: ApiResponse):ResponseCallbacks()
    }