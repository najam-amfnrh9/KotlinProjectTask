package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import models.Products
import network.chaintech.sdpcomposemultiplatform.sdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(list: List<Products>, endOfRecord: () -> Unit) {
    val lazyListState = rememberLazyGridState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Orbix Start Task", maxLines = 1, color = MaterialTheme.colorScheme.primary) },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->

        LazyVerticalGrid(
            state = lazyListState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.sdp),
            horizontalArrangement = Arrangement.spacedBy(10.sdp),
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            content = {
                items(list.size) { index ->
                    val data = list[index]
                    ProductItem(
                        data.title,
                        data.description,
                        data.price.toString(),
                        data.discountPercentage.toString(),
                        data.thumbnail
                    )
                }
                // Detect if we reached the bottom of the list to load more items
                val lastVisibleItemIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                if (lastVisibleItemIndex != null && lastVisibleItemIndex >= list.size - 2) {
                    endOfRecord.invoke()
                }
            }
        )
    }
}

