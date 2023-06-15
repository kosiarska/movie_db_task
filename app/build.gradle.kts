import com.android.build.gradle.ProguardFiles.ProguardFile.OPTIMIZE
import pl.tretowicz.moviedb.Application.APP_NAME
import pl.tretowicz.moviedb.Application.Dev.devApplicationId
import pl.tretowicz.moviedb.Application.Flavour.DEV
import pl.tretowicz.moviedb.Application.Flavour.ENV_DIMENSION
import pl.tretowicz.moviedb.Application.Flavour.PROD
import pl.tretowicz.moviedb.Application.Package.app
import pl.tretowicz.moviedb.Application.Prod.prodApplicationId
import pl.tretowicz.moviedb.Application.SigningConfig.keyAliasValue
import pl.tretowicz.moviedb.Application.SigningConfig.keyPasswordValue
import pl.tretowicz.moviedb.Application.SigningConfig.storeFileName
import pl.tretowicz.moviedb.Application.SigningConfig.storePasswordValue
import pl.tretowicz.moviedb.Application.Variant.DEBUG
import pl.tretowicz.moviedb.Application.Variant.RELEASE
import pl.tretowicz.moviedb.Application.applicationName
import pl.tretowicz.moviedb.Application.devAppName
import pl.tretowicz.moviedb.Proguard.RULES

@Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")
plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.kapt)
  // TODO: Refactor plugins.
  id("dagger.hilt.android.plugin")
  id("com.google.gms.google-services")
  id("com.google.firebase.crashlytics")
}

android {

  buildFeatures {
    compose = true
    viewBinding = true
  }

  signingConfigs {

    getByName(DEBUG) {
      storeFile = file(storeFileName)
      storePassword = storePasswordValue
      keyAlias = keyAliasValue
      keyPassword = keyPasswordValue
    }

    create(RELEASE) {
      storeFile = file(storeFileName)
      storePassword = storePasswordValue
      keyAlias = keyAliasValue
      keyPassword = keyPasswordValue
    }
  }

  buildTypes {

    debug {
      isMinifyEnabled = false
      signingConfig = signingConfigs.getByName(DEBUG)
    }

    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile(OPTIMIZE.fileName), RULES)
      signingConfig = signingConfigs.getByName(RELEASE)
    }
  }

  compileOptions {
    sourceCompatibility = pl.tretowicz.moviedb.Build.Version.java
    targetCompatibility = pl.tretowicz.moviedb.Build.Version.java
  }

  compileSdk = pl.tretowicz.moviedb.Build.Android.Sdk.compile

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompileExtension.get()
  }

  defaultConfig {
    applicationId = app
    minSdk = pl.tretowicz.moviedb.Build.Android.Sdk.min
    targetSdk = pl.tretowicz.moviedb.Build.Android.Sdk.target
  }

  namespace = app

  packagingOptions {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
      excludes.add("META-INF/DEPENDENCIES")
    }
  }

  flavorDimensions += listOf(ENV_DIMENSION)

  productFlavors {

    create(DEV) {
      dimension = ENV_DIMENSION

      applicationId = devApplicationId
      resValue("string", APP_NAME, devAppName())
    }

    create(PROD) {
      dimension = ENV_DIMENSION

      applicationId = prodApplicationId
      resValue("string", APP_NAME, applicationName)
    }
  }
}

dependencies {

  implementation(project(":base"))
  implementation(project(":base-android"))
  implementation(project(":configuration"))
  implementation(project(":movies"))

  implementation(libs.accompanist.navigationAnimation)

  implementation(libs.androidx.core)
  implementation(libs.androidx.splashscreen)

  api(libs.androidx.work)
  api(libs.hilt.work)

  implementation(libs.bundles.lifecycle)
  implementation(libs.bundles.navigation)
  implementation(libs.bundles.compose)
  implementation(libs.bundles.coroutines)
  implementation(libs.bundles.hilt)

  implementation(libs.coil)

  implementation(libs.firebase.analytics.ktx)
  implementation(libs.firebase.crashlytics.ktx)
  implementation("androidx.paging:paging-common-ktx:3.1.1")
  implementation("androidx.paging:paging-compose:3.2.0-beta01")

  debugImplementation(libs.compose.ui.tooling)
  implementation(libs.compose.ui.viewbinding)

  kapt(libs.hilt.compiler)
  kapt(libs.hilt.work.compiler)

  testImplementation(libs.bundles.test)

  implementation(libs.moshi)
  implementation(libs.moshi.kotlin)
  kapt(libs.moshi.codegen)
}
