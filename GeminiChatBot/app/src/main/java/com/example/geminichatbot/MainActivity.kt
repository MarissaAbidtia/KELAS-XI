package com.example.geminichatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

// ✅ Data model pesan
data class Message(val text: String, val isUser: Boolean)

// ✅ ViewModel untuk mengelola chat
class ChatViewModel : ViewModel() {
    var messages by mutableStateOf(listOf<Message>())
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun sendMessage(input: String) {
        if (input.isBlank()) return

        messages = messages + Message(input, true)
        isLoading = true

        // Simulasi respons Gemini (ganti dengan API asli nanti)
        val response = "Gemini menjawab: \"$input\""
        messages = messages + Message(response, false)
        isLoading = false
    }
}

// ✅ Composable utama
@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {
    var input by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F8E9)) // pastel hijau muda
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.messages) { msg ->
                ChatBubble(message = msg)
            }
        }

        if (viewModel.isLoading) {
            Text("Gemini sedang mengetik...", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Tulis pesan...") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.sendMessage(input.text)
                    input = TextFieldValue("")
                },
                enabled = input.text.isNotBlank()
            ) {
                Text("Kirim")
            }
        }
    }
}

// ✅ Bubble chat
@Composable
fun ChatBubble(message: Message) {
    val bubbleColor = if (message.isUser) Color(0xFFFFCDD2) else Color(0xFFB2EBF2) // pastel pink & biru
    val alignment = if (message.isUser) Alignment.End else Alignment.Start

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = alignment
    ) {
        Surface(
            color = bubbleColor,
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 2.dp,
            modifier = Modifier
                .widthIn(max = 300.dp)
                .padding(4.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// ✅ Entry point
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ChatScreen()
            }
        }
    }
}
