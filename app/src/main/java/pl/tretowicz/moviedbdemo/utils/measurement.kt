package pl.tretowicz.moviedbdemo.utils

import android.content.Context
import android.util.DisplayMetrics

fun Context.dpToPx(dp: Float): Float {
  val metrics = resources.displayMetrics
  return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}
