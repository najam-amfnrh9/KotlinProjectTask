package network.states

import models.ApiResponse

data class ResponseStates(
        val isLoading:Boolean = false,
        val errorMessage: String?=null,
        val responseData: ApiResponse?=null
    ) {
        fun toUiState(): ResponseCallbacks {
            return if (isLoading) {
                ResponseCallbacks.Loading
            } else if(errorMessage?.isNotEmpty()==true) {
                ResponseCallbacks.Error(errorMessage)
            } else {
                ResponseCallbacks.Success(responseData!!)
            }
        }
    }