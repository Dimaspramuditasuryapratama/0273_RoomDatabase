package com.example.activity7_roomdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.activity7_roomdatabase.uicontroller.SiswaApp
import com.example.activity7_roomdatabase.ui.theme.Activity7RoomDatabaseTheme // Pastikan nama theme ini sesuai dengan yang ada di ui/theme/Theme.kt Anda

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Activity7RoomDatabaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SiswaApp()
                }
            }
        }
    }
}