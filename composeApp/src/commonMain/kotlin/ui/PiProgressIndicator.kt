package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun PiProgressIndicator(isDialogIndicator: Boolean = true, modifier: Modifier = Modifier) {
    if (isDialogIndicator) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            PiProgressBar(modifier = modifier.fillMaxSize())
        }
    } else {
        PiProgressBar(modifier = modifier.wrapContentHeight())
    }
}

@Composable
fun PiProgressBar(modifier: Modifier) {
    Box(modifier = modifier.padding(2.sdp).padding(20.sdp).background(color = Color.Transparent)) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ErrorBox(onClose: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
            .padding(10.sdp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(2.sdp),
                text = "Record fetching failed, retry.",
                maxLines = 3,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(3.sdp))

            Text(
                modifier = Modifier.padding(2.sdp).background(
                    MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.sdp)
                ).clickable {
                    onClose.invoke()
                },
                text = "Retry.",
                maxLines = 3,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.background,
            )
        }
    }
}