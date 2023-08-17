package br.com.android.rickandmorty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.android.rickandmorty.retrofit.Character
import coil.compose.AsyncImage

@Composable
fun CharacterScreen(character: Character? = null, OnBackClick: () -> Unit) {
    val statusBackgroundColor = when (character?.status) {
        "Dead" -> Color(0xffef4952)
        "Alive" -> Color(0xff59a848)
        else -> Color.Black
    }
    Column(
        Modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        FilledIconButton(
            onClick = OnBackClick,
            Modifier.size(64.dp)
        ) {
            Icon(Icons.Default.ArrowBack, "", Modifier.size(32.dp))
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (character != null) {
                Text(
                    character.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(24.dp))
                Box {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier
                            .size(300.dp)
                            .clipToBounds()
                            .clip(CircleShape)
                            .border(
                                4.dp, statusBackgroundColor, CircleShape
                            ),
                    )
                    Text(
                        modifier = Modifier
                            .offset(y = 12.dp)
                            .background(statusBackgroundColor)
                            .padding(horizontal = 8.dp)
                            .align(Alignment.BottomCenter),
                        text = character.status.uppercase(),
                        fontSize = 24.sp,
                        color = Color.White,
                    )
                }
                Spacer(Modifier.height(24.dp))
                Text(character.species, fontSize = 24.sp)
                Text("Origin: ${character.origin.name}", fontSize = 16.sp)
                Text("Last location: ${character.location.name}", fontSize = 16.sp)
                Text(character.gender, fontSize = 16.sp)
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp),
                    strokeWidth = 4.dp,
                )
            }
        }
    }
}