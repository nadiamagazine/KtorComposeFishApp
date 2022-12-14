package com.example.ktorcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ktorcomposeapp.ui.theme.KtorComposeAppTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorComposeAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "SpeciesListScreen"
                ) {
                    composable("SpeciesListScreen") {
                        SpeciesListScreen(navController = navController)
                    }
                    composable("SpeciesDetailScreen/{speciesName}",
                        arguments = listOf(
                            navArgument("speciesName")
                            {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val speciesName =
                            it.arguments?.getString("speciesName")
                        if (speciesName != null) {
                            SpeciesDetailScreen(speciesName = speciesName)
                        }
                    }
                }
            }
        }
        Timber.plant(Timber.DebugTree())
    }
}
