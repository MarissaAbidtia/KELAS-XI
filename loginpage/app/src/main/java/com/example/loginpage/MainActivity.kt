package com.example.loginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

// 🎨 Warna pastel
val PastelBlue = Color(0xFFB3E5FC)
val PastelPink = Color(0xFFF8BBD0)
val PastelGreen = Color(0xFFC8E6C9)
val PastelGray = Color(0xFFFAFAFA)

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var loginSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PastelGray)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🖼️ Dummy gambar/icon di atas form
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(PastelBlue),
            contentAlignment = Alignment.Center
        ) {
            Text("👤", fontSize = 48.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Login", fontSize = 32.sp, modifier = Modifier.padding(bottom = 24.dp))

        EmailField(email, emailError) { email = it }
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(password, passwordError) { password = it }
        Spacer(modifier = Modifier.height(24.dp))

        LoginButton(
            onClick = {
                emailError = !isValidEmail(email)
                passwordError = password.length < 6
                loginSuccess = !emailError && !passwordError
            },
            success = loginSuccess
        )

        if (loginSuccess) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Login berhasil 🎉", color = PastelGreen)
        }
    }
}

@Composable
fun EmailField(value: String, isError: Boolean, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Email") },
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )
    if (isError) {
        Text("Email tidak valid", color = Color.Red, fontSize = 12.sp)
    }
}

@Composable
fun PasswordField(value: String, isError: Boolean, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Password") },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        visualTransformation = PasswordVisualTransformation(),
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )
    if (isError) {
        Text("Minimal 6 karakter", color = Color.Red, fontSize = 12.sp)
    }
}

@Composable
fun LoginButton(onClick: () -> Unit, success: Boolean) {
    val animatedColor by animateColorAsState(
        targetValue = if (success) PastelGreen else PastelPink
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(animatedColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text("Login", fontSize = 18.sp)
    }
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
