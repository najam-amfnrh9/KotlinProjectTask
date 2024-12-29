package viewmodels.server

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.ApiStatus
import network.NetworkRepository
import network.states.ResponseCallbacks
import network.states.ResponseStates

class HomeViewModel(private val networkRepository: NetworkRepository) {

    private val _responseStates = MutableStateFlow(ResponseStates())
    private val _homeViewState: MutableStateFlow<ResponseCallbacks> =
        MutableStateFlow(ResponseCallbacks.Loading)
    val homeViewState = _homeViewState.asStateFlow()

    private var pageCounter = 1
    private val limit = 20

    suspend fun getProducts() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                networkRepository.getProductList(limit = limit * pageCounter).collect { response ->
                    when (response.status) {
                        ApiStatus.LOADING -> {
                            _responseStates.update { it.copy(isLoading = true) }
                        }

                        ApiStatus.SUCCESS -> {
                            _responseStates.update {
                                it.copy(
                                    isLoading = false, errorMessage = "",
                                    responseData = response.data
                                )
                            }
                            pageCounter++
                        }

                        ApiStatus.ERROR -> {
                            _responseStates.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = response.message
                                )
                            }
                        }
                    }
                    _homeViewState.value = _responseStates.value.toUiState()
                }
            } catch (e: Exception) {
                _responseStates.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to fetch data"
                    )
                }
            }
        }
    }
}

