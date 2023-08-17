package br.com.android.rickandmorty.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.android.rickandmorty.retrofit.Character
import coil.compose.AsyncImage

@Composable
fun CharacterCard(character: Character, onClick: () -> Unit) {
    val statusBackgroundColor = when (character.status) {
        "Dead" -> Color(0xffef4952)
        "Alive" -> Color(0xff59a848)
        else -> Color.Black
    }
    Card(
        modifier = Modifier
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .clipToBounds()
                        .clip(CircleShape)
                        .border(
                            2.dp, statusBackgroundColor, CircleShape
                        ),
                )
                Text(
                    modifier = Modifier
                        .offset(y = 7.dp)
                        .background(statusBackgroundColor)
                        .padding(horizontal = 8.dp)
                        .align(Alignment.BottomCenter),
                    text = character.status.uppercase(),
                    fontSize = 14.sp,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = character.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}