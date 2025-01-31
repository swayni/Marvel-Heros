package sw.swayni.base.mvvm.enums

import androidx.navigation.NavDirections

sealed class NavigationAction {
    class ToDirection(val direction: NavDirections) : NavigationAction()
    data object Back : NavigationAction()
}
