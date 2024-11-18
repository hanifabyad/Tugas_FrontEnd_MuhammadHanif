package com.example.frontenddd.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.frontenddd.R
    import com.example.frontenddd.Model.Profil
import com.example.frontenddd.navigation.NavigationItem
import com.example.frontenddd.navigation.Screen
import com.example.frontenddd.presentation.DetailScreen.DetailResepScreen
import com.example.frontenddd.presentation.GridItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivity (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TopBar") },
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xffe3f2fd))
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.About.route,
            modifier = modifier.padding(contentPadding)
        ) {
            composable(Screen.About.route) {
                About(
                    profil = Profil(1, "Muhammad Hanif", "4342301038", "Politeknik Negeri Batam", "Teknik Informatika", R.drawable.hanif),
                    navController = NavController(context)
                )
            }

            composable(Screen.Grid.route) {
                GridScreen(navController)
            }

            composable(Screen.HomeScreen.route) {
                HomeScreen(navController)
            }

            composable(
                Screen.DetailResep.route + "/{resepid}",
                arguments = listOf(navArgument("resepid") { type = NavType.IntType })
            ) { navBackStackEntry ->
                DetailResepScreen(
                    navController = navController,
                    resepid = navBackStackEntry.arguments?.getInt("resepid")
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xffe3f2fd) // Perhatikan penempatan modifier dan containerColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_about),
                icon = Icons.Default.Home,
                screen = Screen.About
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_HomeScreen),
                icon = Icons.AutoMirrored.Filled.List,
                screen = Screen.HomeScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_Grid),
                icon = Icons.AutoMirrored.Filled.List,
                screen = Screen.Grid
            )
        )
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) }
            )
        }
    }
}


@Preview
@Composable
private fun PreviewBottomBar() {
   MainActivity()
}