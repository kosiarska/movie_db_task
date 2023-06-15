@file:Suppress("KotlinConstantConditions")

package pl.tretowicz.moviedbdemo.extension

import pl.tretowicz.moviedb.configuration.BuildConfig

fun isProdFlavor(): Boolean = BuildConfig.FLAVOR == "prod"
