package com.jetpackcomposse.firstcomposserapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PromptingScreen()
        }
    }
}

@Composable
fun PromptingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 16.dp)
    ) {
        Text(text = "Hello World!", fontSize = 16.sp)
        Text(text = "Good Morning", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7C4DFF),
                contentColor = Color.White
            ),
            modifier = Modifier
                .width(110.dp)
                .height(40.dp)
        ) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPromptingScreen() {
    PromptingScreen()
}