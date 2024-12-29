
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import di.appModule
import org.koin.compose.KoinApplication
import theme.KotlinProjectTheme
import ui.home.HomeScreen

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {
        KotlinProjectTheme {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                HomeScreen()
            }
        }
    }

}