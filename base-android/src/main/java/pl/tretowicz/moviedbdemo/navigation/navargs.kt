package pl.tretowicz.moviedbdemo.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import java.net.URLDecoder.decode
import java.net.URLEncoder.encode
import java.nio.charset.StandardCharsets.UTF_8

fun stringArgFor(key: String): NamedNavArgument =
  navArgument(name = key, builder = { type = NavType.StringType })

fun deepLinkFor(uri: String): NavDeepLink =
  navDeepLink { uriPattern = uri }

fun String.encode(): String =
  encode(this, UTF_8.toString())

fun String.decode(): String =
  decode(this, UTF_8.toString())

