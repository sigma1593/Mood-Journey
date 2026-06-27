package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date

// MODELO
data class MoodEntry(
    val mood: String,
    val note: String,
    val date: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var screen by remember { mutableStateOf("auth") }

            // AUTH
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var message by remember { mutableStateOf("") }

            // REGISTER
            var regUser by remember { mutableStateOf("") }
            var regPass by remember { mutableStateOf("") }
            var users by remember { mutableStateOf(listOf<Pair<String, String>>()) }

            // MOOD
            var selectedMood by remember { mutableStateOf("") }
            var note by remember { mutableStateOf("") }
            var history by remember { mutableStateOf(listOf<MoodEntry>()) }

            // ANIMACIÓN EMOJI
            val scale by animateFloatAsState(
                targetValue = if (selectedMood.isNotEmpty()) 1.2f else 1f,
                animationSpec = tween(200),
                label = "emojiScale"
            )

            // =========================
            // 🟢 AUTH SCREEN
            // =========================
            AnimatedVisibility(
                visible = screen == "auth",
                enter = fadeIn(tween(500)) + scaleIn(),
                exit = fadeOut(tween(300))
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("Welcome 👋", style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            val exists = users.any { it.first == username && it.second == password }
                            if (exists) {
                                screen = "mood"
                            } else {
                                message = "Invalid login"
                            }
                        }
                    ) {
                        Text("Login")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = { screen = "register" }) {
                        Text("Register")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(message)
                }
            }

            // =========================
            // 🟡 REGISTER SCREEN
            // =========================
            AnimatedVisibility(
                visible = screen == "register",
                enter = fadeIn(tween(500)) + slideInVertically(),
                exit = fadeOut()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("Register", style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = regUser,
                        onValueChange = { regUser = it },
                        label = { Text("New Username") }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = regPass,
                        onValueChange = { regPass = it },
                        label = { Text("New Password") }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            users = users + (regUser to regPass)
                            screen = "auth"
                        }
                    ) {
                        Text("Save User")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = { screen = "auth" }) {
                        Text("Back")
                    }
                }
            }

            // =========================
            // 🟡 MOOD SCREEN
            // =========================
            AnimatedVisibility(
                visible = screen == "mood",
                enter = fadeIn(tween(500)),
                exit = fadeOut()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("Mood Tracker", style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text("Hello $username 👋")

                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        listOf("😊", "😐", "😢", "😡", "😴").forEach { mood ->
                            Button(
                                onClick = { selectedMood = mood },
                                modifier = Modifier
                                    .padding(4.dp)
                                    .graphicsLayer(
                                        scaleX = if (selectedMood == mood) scale else 1f,
                                        scaleY = if (selectedMood == mood) scale else 1f
                                    )
                            ) {
                                Text(mood)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = note,
                        onValueChange = { note = it },
                        label = { Text("Write a note") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            val date = SimpleDateFormat("yyyy-MM-dd").format(Date())

                            history = history + MoodEntry(selectedMood, note, date)

                            selectedMood = ""
                            note = ""
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Mood")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { screen = "history" },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("History")
                    }
                }
            }

            // =========================
            // 🔵 HISTORY SCREEN
            // =========================
            AnimatedVisibility(
                visible = screen == "history",
                enter = fadeIn(tween(500)) + slideInHorizontally(),
                exit = fadeOut()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("History", style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text("Total records: ${history.size}")

                    Spacer(modifier = Modifier.height(20.dp))

                    history.reversed().forEach {

                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(tween(300)) + slideInVertically()
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(it.mood)
                                    Text(it.note)
                                    Text(it.date)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = { screen = "mood" }) {
                        Text("Back")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = { screen = "auth" }) {
                        Text("Logout")
                    }
                }
            }
        }
    }
}