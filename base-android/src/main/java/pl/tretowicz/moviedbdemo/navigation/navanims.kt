package pl.tretowicz.moviedbdemo.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun fadeInEnter(): EnterTransition =
  linearFadeIn()

fun slideInEnter(): EnterTransition =
  slideIn(offsetX = { it })

fun slideInPopEnter(): EnterTransition =
  slideIn(offsetX = { -it })

fun slideOutExit(): ExitTransition =
  slideOut(offsetX = { -it })

fun slideOutPopExit(): ExitTransition =
  slideOut(offsetX = { it })

private fun linearFadeIn(fadeDurationMillis: Int = 500): EnterTransition =
  fadeIn(animationSpec = tween(fadeDurationMillis))

private fun slideIn(
  offsetX: (fullWidth: Int) -> Int,
  slideDurationMillis: Int = 300,
  fadeDurationMillis: Int = 300
): EnterTransition =
  slideInHorizontally(
    initialOffsetX = offsetX,
    animationSpec = tween(slideDurationMillis)
  ) + fadeIn(animationSpec = tween(fadeDurationMillis))

private fun slideOut(
  offsetX: (fullWidth: Int) -> Int,
  slideDurationMillis: Int = 300,
  fadeDurationMillis: Int = 300
): ExitTransition =
  slideOutHorizontally(
    targetOffsetX = offsetX,
    animationSpec = tween(durationMillis = slideDurationMillis)
  ) + fadeOut(animationSpec = tween(fadeDurationMillis))
