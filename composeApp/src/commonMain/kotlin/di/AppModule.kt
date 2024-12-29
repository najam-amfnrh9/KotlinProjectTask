package di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import network.NetworkRepository
import org.koin.dsl.module
import viewmodels.server.HomeViewModel
import viewmodels.ui.UiStateViewModel


fun appModule() = listOf(provideHttpClientModule, provideRepositoryModule, provideViewModelsModule)

val provideHttpClientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
}

val provideRepositoryModule = module {
    single<NetworkRepository> { NetworkRepository(get()) }
}

val provideViewModelsModule = module {
    single {
        HomeViewModel(get())
    }

    single {
        UiStateViewModel()
    }
}