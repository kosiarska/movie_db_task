import Build_gradle.Configuration.API_CONNECTION_TIMEOUT
import Build_gradle.Configuration.API_READ_TIMEOUT
import Build_gradle.Configuration.API_SECRET
import Build_gradle.Configuration.API_URL
import Build_gradle.Configuration.API_WRITE_TIMEOUT
import Build_gradle.Configuration.BOOLEAN
import Build_gradle.Configuration.ENABLE_CRASHLYTICS
import Build_gradle.Configuration.ENABLE_TIMBER
import Build_gradle.Configuration.LOG_LEVEL
import Build_gradle.Configuration.LONG
import Build_gradle.Configuration.STRING
import com.android.build.gradle.ProguardFiles.ProguardFile.OPTIMIZE
import pl.tretowicz.moviedb.Application.Flavour.DEV
import pl.tretowicz.moviedb.Application.Flavour.ENV_DIMENSION
import pl.tretowicz.moviedb.Application.Flavour.PROD
import pl.tretowicz.moviedb.Application.Package.configuration
import pl.tretowicz.moviedb.Proguard.CONSUMER
import pl.tretowicz.moviedb.Proguard.RULES
import pl.tretowicz.moviedb.Build.Android.Sdk
import pl.tretowicz.moviedb.Build.Version

object Configuration {

  const val STRING = "String"
  const val BOOLEAN = "boolean"
  const val LONG = "long"

  const val API_CONNECTION_TIMEOUT = "API_CONNECTION_TIMEOUT"
  const val API_READ_TIMEOUT = "API_READ_TIMEOUT"
  const val API_URL = "API_URL"
  const val API_SECRET = "API_SECRET"
  const val API_WRITE_TIMEOUT = "API_WRITE_TIMEOUT"
  const val ENABLE_CRASHLYTICS = "ENABLE_CRASHLYTICS"
  const val ENABLE_TIMBER = "ENABLE_TIMBER"
  const val LOG_LEVEL = "LOG_LEVEL"
}


@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.kapt)
}

android {

  buildFeatures {
    buildConfig = true
  }

  buildTypes {

    debug {

      val enableCrashlytics = findProperty("EnableCrashlyticsDebug").toString()
      buildConfigField(BOOLEAN, ENABLE_CRASHLYTICS, enableCrashlytics)

      val enableLogger = findProperty("EnableTimberDebug").toString()
      buildConfigField(BOOLEAN, ENABLE_TIMBER, enableLogger)

      val logLevel = findProperty("LogLevelDebug").toString()
      buildConfigField(STRING, LOG_LEVEL, logLevel)
    }

    release {

      val enableCrashlytics = findProperty("EnableCrashlyticsRelease").toString()
      buildConfigField(BOOLEAN, ENABLE_CRASHLYTICS, enableCrashlytics)

      val enableLogger = findProperty("EnableTimberRelease").toString()
      buildConfigField(BOOLEAN, ENABLE_TIMBER, enableLogger)

      val logLevel = findProperty("LogLevelRelease").toString()
      buildConfigField(STRING, LOG_LEVEL, logLevel)

      proguardFiles(getDefaultProguardFile(OPTIMIZE.fileName), RULES)
    }
  }

  buildTypes.all {
    val connectionTimeout = findProperty("ApiConnectionTimeout").toString()
    buildConfigField(LONG, API_CONNECTION_TIMEOUT, connectionTimeout)

    val readTimeout = findProperty("ApiReadTimeout").toString()
    buildConfigField(LONG, API_READ_TIMEOUT, readTimeout)

    val writeTimeout = findProperty("ApiWriteTimeout").toString()
    buildConfigField(LONG, API_WRITE_TIMEOUT, writeTimeout)
  }

  compileOptions {
    sourceCompatibility = Version.java
    targetCompatibility = Version.java
  }
  compileSdk = Sdk.compile

  defaultConfig {
    consumerProguardFiles(CONSUMER)
    minSdk =  Sdk.min
  }
  namespace = configuration

  flavorDimensions += listOf(ENV_DIMENSION)

  productFlavors {

    create(DEV) {
      dimension = ENV_DIMENSION

      val apiUrl = findProperty("ApiUrlDebug").toString()
      buildConfigField(STRING, API_URL, apiUrl)

      val apiSecret = findProperty("ApiSecretDebug").toString()
      buildConfigField(STRING, API_SECRET, apiSecret)
    }

    create(PROD) {
      dimension = ENV_DIMENSION

      val apiUrl = findProperty("ApiUrlProd").toString()
      buildConfigField(STRING, API_URL, apiUrl)

      val apiSecret = findProperty("ApiSecretRelease").toString()
      buildConfigField(STRING, API_SECRET, apiSecret)
    }
  }
}

dependencies {
  implementation(libs.bundles.hilt)
  kapt(libs.hilt.compiler)
}
