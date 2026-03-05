package com.example.quiz_app_starter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz_app_starter.ui.theme.QuizappstarterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizappstarterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainMenuScreen(
                        bestScore = 3,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainMenuScreen(
    bestScore: Int = 0,
    modifier: Modifier
) {
    val darkTheme = isSystemInDarkTheme()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Best Quiz App",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = buildAnnotatedString {
                append("Test your ")
                listOf(Color.Red, Color(0xFFFA9531), Color.Yellow, Color.Green, Color(0xFF0BC9B8), Color.Cyan, Color(
                    0xFF4E89EE
                ), Color(
                    0xFF673AB7), Color.Magenta).forEachIndexed { index, color ->
                    withStyle(style = SpanStyle(color = color, fontWeight = FontWeight.ExtraBold)) {
                        append("KNOWLEDGE!"[index].toString())
                    }
                }
                val word = "KNOWLEDGE!"
                word.drop(9).forEach { char ->
                    withStyle(style = SpanStyle(color = Color.Magenta, fontWeight = FontWeight.ExtraBold)) {
                        append(char.toString())
                    }
                }
            },
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = if (darkTheme) Color.White else Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(24.dp),
                    ambientColor = if (darkTheme) Color.White else Color.Black,
                    spotColor = if (darkTheme) Color.White else Color.Black
                )
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Best of all time",
                    fontWeight = FontWeight.Bold,
                    color = if (darkTheme) Color.White else Color.Black
                )
                Text(
                    text = bestScore.toString(),
                    fontSize = 64.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (darkTheme) Color.White else Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { android.util.Log.d("MainMenu", "Play button clicked!") },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(50.dp),
                    ambientColor = if (darkTheme) Color.White else Color.Black,
                    spotColor = if (darkTheme) Color.White else Color.Black
                ),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 2.dp
            )
        ) {
            Text(
                text = "🎮 Play!",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = if (darkTheme) Color.White else Color.Black
            )
        }
    }
}

@Preview(showBackground = true, name = "Light", device = "spec:width=411dp,height=891dp,dpi=420", showSystemUi = true)
@Preview(showBackground = true, name = "Dark", device = "spec:width=411dp,height=891dp,dpi=420", showSystemUi = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainMenuScreenPreview() {
    QuizappstarterTheme {
        MainMenuScreen(12, Modifier)
    }
}