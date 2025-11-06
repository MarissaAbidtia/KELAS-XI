package com.example.statedemo

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
            StateDemoScreen()
        }
    }
}

// 🎨 Warna pastel
val PastelBlue = Color(0xFFB3E5FC)
val PastelGreen = Color(0xFFC8E6C9)
val PastelPink = Color(0xFFF8BBD0)
val PastelGray = Color(0xFFF5F5F5)

@Composable
fun StateDemoScreen() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    val greeting by remember(name) {
        derivedStateOf {
            if (name.text.isBlank()) "Halo, pengguna" else "Halo, ${name.text}"
        }
    }

    val greetingColor by animateColorAsState(
        targetValue = if (name.text.length > 5) PastelGreen else PastelBlue
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PastelGray)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingText(greeting, greetingColor)
        Spacer(modifier = Modifier.height(24.dp))
        NameInputField(name) { name = it }
        Spacer(modifier = Modifier.height(16.dp))
        ResetButton { name = TextFieldValue("") }
    }
}

@Composable
fun GreetingText(text: String, color: Color) {
    Text(
        text = text,
        fontSize = 28.sp,
        color = color,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun NameInputField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Masukkan nama") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ResetButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(PastelPink)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text("Reset", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStateDemoScreen() {
    StateDemoScreen()
}
