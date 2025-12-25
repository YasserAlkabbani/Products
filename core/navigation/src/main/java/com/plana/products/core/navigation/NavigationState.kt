package com.plana.products.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator


data class NavigationState(
    val startKey: NavKey,
    val navBackStack: NavBackStack<NavKey>
) {
    val currentKey: NavKey by derivedStateOf { navBackStack.last() }
}

@Composable
fun rememberNavigationState(startKey: NavKey): NavigationState {

    val navBackStack = rememberNavBackStack(startKey)

    return remember(startKey, navBackStack) {
        NavigationState(
            startKey = startKey,
            navBackStack = navBackStack
        )
    }
}


@Composable
fun NavigationState.asEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): List<NavEntry<NavKey>> {

    val entryDecorators = listOf(
        rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
        rememberViewModelStoreNavEntryDecorator<NavKey>()
    )
    return rememberDecoratedNavEntries(
        backStack = navBackStack,
        entryDecorators = entryDecorators,
        entryProvider = entryProvider,
    )

}