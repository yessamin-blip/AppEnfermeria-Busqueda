package com.appenfermeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.appenfermeria.ui.HomeScreen
import com.appenfermeria.ui.SearchScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MaterialTheme {

                //guardo en q pantalla estoy, al cambiarla se pinta la otra
                var pantallaActual by remember { mutableStateOf("home") }

                if (pantallaActual == "home") {

                    HomeScreen(onNavigateToSearch = { pantallaActual = "busqueda" })

                } else if (pantallaActual == "busqueda") {

                    SearchScreen(onNavigateBack = { pantallaActual = "home" })

                }

            }

        }

    }

}
