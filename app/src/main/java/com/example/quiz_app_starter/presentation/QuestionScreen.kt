package com.example.quiz_app_starter.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quiz_app_starter.model.Question
import com.example.quiz_app_starter.model.getDummyQuestions
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.quiz_app_starter.ui.theme.QuizappstarterTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    questions: List<Question> = getDummyQuestions(),
    currentQuestionIndex: Int = 0
) {
    val question = questions[currentQuestionIndex]
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    val timerDurationSeconds = 30
    var timeLeft by remember { mutableStateOf(timerDurationSeconds) }

    LaunchedEffect(key1 = currentQuestionIndex) {
        timeLeft = timerDurationSeconds
        while (timeLeft > 0) {
            kotlinx.coroutines.delay(1000L)
            timeLeft--
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
            title = {
                Text(
                    "Quiz App",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            actions = {
                IconButton(onClick = { android.util.Log.d("QuestionScreen", "Logout clicked!") }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )
        },
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(16.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text(
                        "Submit",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LinearProgressIndicator(
                progress = { timeLeft / timerDurationSeconds.toFloat() },
                modifier = Modifier.fillMaxWidth()
            )
            Card(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = question.question,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            LazyColumn {
                items(question.answers) { answer ->
                    AnswerCard(
                        answer = answer,
                        isSelected = answer == selectedAnswer,
                        onSelect = { selectedAnswer = answer }
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerCard(
    answer: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.secondary
                )
            )
            Text(text = answer)
        }
    }

}
@Preview(showBackground = true, name = "Light", device = "spec:width=411dp,height=891dp,dpi=420", showSystemUi = true)
@Preview(showBackground = true, name = "Dark", device = "spec:width=411dp,height=891dp,dpi=420", showSystemUi = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun QuestionScreenPreview() {
    QuizappstarterTheme { QuestionScreen() }
}