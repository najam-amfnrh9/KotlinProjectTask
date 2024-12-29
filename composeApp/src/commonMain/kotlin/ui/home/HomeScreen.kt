package ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import models.Products
import network.states.ResponseCallbacks
import org.koin.compose.getKoin
import ui.ErrorBox
import ui.PiProgressIndicator
import viewmodels.server.HomeViewModel
import viewmodels.ui.UiStateViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = getKoin().get()
    val uiViewModel: UiStateViewModel = getKoin().get()
    val homeScreenState by viewModel.homeViewState.collectAsState()
    val requestResults = rememberSaveable { mutableStateOf(true) }
    val isFirstTimeLoading = rememberSaveable { mutableStateOf(true) }
    val list = remember { mutableStateListOf<Products>() }

    LaunchedEffect(requestResults.value) {
        if (requestResults.value) {
            viewModel.getProducts()
            requestResults.value = false
        }
    }

    when (homeScreenState) {
        is ResponseCallbacks.Loading -> {
            PiProgressIndicator(isFirstTimeLoading.value)
        }

        is ResponseCallbacks.Success -> {
            val products = (homeScreenState as ResponseCallbacks.Success).responseData.list
            list.addAll(products)
            ProductCard(list){
                requestResults.value = true
            }
        }

        is ResponseCallbacks.Error -> {
            uiViewModel.openErrorDialog.value = true
            if (uiViewModel.openErrorDialog.value) {
                ErrorBox {
                    uiViewModel.openErrorDialog.value = false
                    requestResults.value = true
                }
            }
        }
    }
}

