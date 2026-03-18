package com.aulascom.fluxodetelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aulascom.fluxodetelas.ui.theme.FluxoDeTelasTheme
import androidx.navigation.compose.rememberNavController
import com.aulascom.fluxodetelas.screen.LoginScreen
import com.aulascom.fluxodetelas.screen.MenuScreen
import com.aulascom.fluxodetelas.screen.PedidoScreen
import com.aulascom.fluxodetelas.screen.PerfilScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FluxoDeTelasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ){
                        composable("login") { LoginScreen(navController) }
                        composable("menu") { MenuScreen(navController) }
                        composable("pedido") { PedidoScreen(navController) }
                        composable("perfil") { PerfilScreen(navController) }
                    }
                }
            }
        }
    }
}

