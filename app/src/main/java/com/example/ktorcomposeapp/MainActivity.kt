package com.example.ktorcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ktorcomposeapp.ui.theme.KtorComposeAppTheme
import com.example.ktorcomposeapp.viewmodel.SpeciesViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private val viewModel: SpeciesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorComposeAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "SpeciesListScreen"
                ) {
                    composable("SpeciesListScreen/{SpeciesResponse}") {
                        SpeciesListScreen(navController, viewModel)
                    }
                    composable("SpeciesDetailScreen/{speciesName}",
                        arguments = listOf(
                            navArgument("speciesName")
                            {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val speciesName = remember {
                            it.arguments?.getString("speciesName")
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.getSpeciesName()
        }
        Timber.plant(Timber.DebugTree())
    }
}
