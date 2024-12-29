package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import models.ApiResponse
import theme.log

class NetworkRepository(private val httpClient: HttpClient) {

    fun getProductList(limit: Int): Flow<NetWorkResult<ApiResponse?>> {
        return toResultFlow {
            val url = "${Constants.BASE_URL}products?limit=$limit&skip=5"
            log("url->$url")
            val response = httpClient.get(url)
                .body<ApiResponse>()
            NetWorkResult.Success(response)
        }
    }


}