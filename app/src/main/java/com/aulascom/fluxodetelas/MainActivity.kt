package com.aulascom.fluxodetelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import com.aulascom.fluxodetelas.ui.theme.FluxoDeTelasTheme
import androidx.navigation.compose.rememberNavController
import com.aulascom.fluxodetelas.screen.LoginScreen
import com.aulascom.fluxodetelas.screen.MenuScreen
import com.aulascom.fluxodetelas.screen.PedidoScreen
import com.aulascom.fluxodetelas.screen.PerfilScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(1000)
                            ) + fadeOut(animationSpec = tween(2000))
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(1000)
                            )
                        }
                    ){
                        //Composable login
                        composable(
                            "login"
                        ) {
                            LoginScreen(navController)
                        }

                        //Composable menu
                        composable(
                            "menu"
                        ) {
                            MenuScreen(navController)
                        }

                        //Composable pedido
                        composable(
                            "pedido?numeroPedido={numeroPedido}",
                            arguments = listOf(
                                navArgument("numeroPedido"){
                                    defaultValue = "Sem Pedidos"
                                }
                            )
                        ) {
                            val numeroPedido = it.arguments?.getString("numeroPedido")

                            PedidoScreen(
                                navController,
                                numeroPedido = numeroPedido!!
                            )
                        }

                        //Composable perfil
                        composable(
                            route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument("nome"){
                                    type = NavType.StringType
                                },
                                navArgument("idade"){
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            navBackStackEntry ->
                            val nome = navBackStackEntry.arguments?.getString("nome")
                            val idade = navBackStackEntry.arguments?.getInt("idade")
                            PerfilScreen(
                                navController,
                                nome = nome!!,
                                idade = idade!!
                            )
                        }
                    }
                }
            }
        }
    }
}

