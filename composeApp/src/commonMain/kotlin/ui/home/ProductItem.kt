package ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.seiko.imageloader.rememberImagePainter
import network.chaintech.sdpcomposemultiplatform.sdp
import ui.PiProgressBar

@Composable
fun ProductItem(name: String, description: String, price: String, discount: String, image: String) {
    Card(
        modifier = Modifier.padding(8.sdp).fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.sdp
        ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
    ) {
        val painter = rememberImagePainter(image)
        Column(modifier = Modifier.height(320.sdp).padding(10.sdp)) {
            Box(
                modifier = Modifier.height(120.sdp)
                    .padding(4.sdp).align(Alignment.CenterHorizontally)
                    .clip(MaterialTheme.shapes.medium),
            ) {
                PiProgressBar(modifier = Modifier.height(120.sdp))
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }

            Spacer(modifier = Modifier.height(6.sdp))
            Column {
                Text(
                    modifier = Modifier.padding(2.sdp),
                    text = name,
                    maxLines = 3,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(3.sdp))
                Text(
                    modifier = Modifier.padding(2.sdp),
                    text = description,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(2.sdp),
                    text = "$$price",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(modifier = Modifier.height(3.sdp))
                Text(
                    modifier = Modifier.padding(2.sdp),
                    text = "$discount% discount",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(red = 0.1f, green = 0.8f, blue = 0.0f)
                )
                Spacer(modifier = Modifier.height(3.sdp))
            }
        }
    }
}