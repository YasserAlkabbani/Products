package com.plana.products.core.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val navigationState: NavigationState) {

    fun navigate(navKey: NavKey) {
        navigationState.navBackStack.add(navKey)
    }

    fun goBack() {
        navigationState.navBackStack.removeLastOrNull()
    }

}