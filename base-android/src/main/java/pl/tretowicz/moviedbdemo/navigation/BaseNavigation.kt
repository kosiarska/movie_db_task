package pl.tretowicz.moviedbdemo.navigation

import androidx.navigation.NavController

abstract class BaseNavigation(private val controller: NavController) {

  fun up() =
    controller.navigateUp()
}
