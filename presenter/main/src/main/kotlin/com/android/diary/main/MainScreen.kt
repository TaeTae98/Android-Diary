package com.android.diary.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.diary.domain.isTrue
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) = Scaffold(
    modifier = modifier,
    bottomBar = { MainBottomBar(navController = navController) }
) {
    MainNavHost(
        modifier = Modifier.padding(it),
        navController = navController
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) = AnimatedNavHost(
    modifier = modifier,
    navController = navController,
    startDestination = "memo",
    route = "main"
) {
    composable("memo") {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "memo")
        }
    }
    composable("payment") {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "payment")
        }
    }
    composable("tag") {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "tag")
        }
    }
    composable("file") {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "file")
        }
    }
    composable("more") {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "more")
        }
    }
}

@Composable
private fun MainBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) = NavigationBar(
    modifier = modifier,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    MainNavigationItem.values().forEach { item ->
        val label = stringResource(id = item.label)
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == item.route }.isTrue(),
            onClick = { navController.navigate(item.route) },
            icon = { Icon(imageVector = item.icon, contentDescription = label) },
            label = { Text(text = label) },
            alwaysShowLabel = false
        )
    }
}