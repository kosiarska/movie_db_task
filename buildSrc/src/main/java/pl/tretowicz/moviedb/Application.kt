@file:Suppress("MemberVisibilityCanBePrivate")

package pl.tretowicz.moviedb

import pl.tretowicz.moviedb.Application.Package.app

object Application {

  const val APP_NAME = "app_name"
  const val applicationName = "Movie db"
  private const val debugNameSuffix = "Dev"

  object Package {
    const val app = "pl.tretowicz.moviedb"
    const val baseAndroid = "$app.base_android"
    const val commonNetwork = "$app.common_network"
    const val configuration = "$app.configuration"
    const val movies = "$app.movies"
   }

  object Dev {
    private const val dev = "dev"
    const val devApplicationId = "$app.$dev"
  }

  object Prod {
    const val prodApplicationId = "pl.tretowicz.moviedb"
  }

  object SigningConfig {
    const val storeFileName = "androidkey.jks"
    const val storePasswordValue = "moviedb123"
    const val keyAliasValue = "moviedb"
    const val keyPasswordValue = "moviedb123"
  }

  object Variant {
    const val DEBUG = "debug"
    const val RELEASE = "release"
  }

  object Flavour {
    const val DEV = "dev"
    const val PROD = "prod"

    const val ENV_DIMENSION = "environment"
  }

  /**
   * Provides a dev application name.
   */
  fun devAppName(): String = "$applicationName $debugNameSuffix"
}
