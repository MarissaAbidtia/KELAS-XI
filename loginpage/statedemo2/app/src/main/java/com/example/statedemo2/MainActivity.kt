package com.example.statedemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateExample()
        }
    }
}

// 🎨 Warna pastel
val LightBlue = Color(0xFFB3E5FC)
val LightGreen = Color(0xFFC8E6C9)
val LightPink = Color(0xFFF8BBD0)
val LightGray = Color(0xFFF5F5F5)

@Composable
fun StateExample() {
    var username by remember { mutableStateOf(TextFieldValue("")) }

    val greetingText by remember(username) {
        derivedStateOf {
            if (username.text.isBlank()) "Welcome, Guest" else "Welcome, ${username.text}"
        }
    }

    val greetingColor by animateColorAsState(
        targetValue = if (username.text.length > 5) LightGreen else LightBlue
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = greetingText,
                fontSize = 28.sp,
                color = greetingColor,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Enter your name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { username = TextFieldValue("") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = LightPink)
            ) {
                Text("Clear")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStateExample() {
    StateExample()
}
