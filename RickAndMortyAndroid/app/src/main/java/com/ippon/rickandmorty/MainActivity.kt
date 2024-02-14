package com.ippon.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.apollographql.apollo3.ApolloClient
import com.ippon.rickandmorty.service.CharacterService
import com.ippon.rickandmorty.service.GraphqlCharacterService
import com.ippon.rickandmorty.viewmodels.CharacterViewModel
import com.ippon.rickandmorty.views.detailCharacter.DetailCharacter
import com.ippon.rickandmorty.views.helpers.MainScaffold
import com.ippon.rickandmorty.views.listCharacter.CharacterList
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MainActivity : ComponentActivity() {
    private val apolloClient = ApolloClient.Builder().serverUrl(Constants.API_URL).build()
    private val appModule = module {
        single<CharacterService> { GraphqlCharacterService(apolloClient) }
        viewModel { CharacterViewModel(get()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(appModule)
        }

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "character") {
                composable("character") {
                    MainScaffold {
                        CharacterList(
                            onCharacterSelect = {
                                navController.navigate("character/$it")
                            }
                        )
                    }
                }

                composable(
                    "character/{id}",
                    arguments = listOf(navArgument("id") { defaultValue = 0 })
                ) {
                    it.arguments?.getInt("id")?.let { it1 ->
                        DetailCharacter(id = it1, navController)
                    }
                }
            }
        }

    }

}
