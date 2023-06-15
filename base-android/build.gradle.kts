import com.android.build.gradle.ProguardFiles.ProguardFile.OPTIMIZE
import pl.tretowicz.moviedb.Application.Flavour.DEV
import pl.tretowicz.moviedb.Application.Flavour.ENV_DIMENSION
import pl.tretowicz.moviedb.Application.Flavour.PROD
import pl.tretowicz.moviedb.Application.Package.baseAndroid
import pl.tretowicz.moviedb.Proguard.CONSUMER
import pl.tretowicz.moviedb.Proguard.RULES

@Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")
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
    release {
      proguardFiles(getDefaultProguardFile(OPTIMIZE.fileName), RULES)
    }
  }

  compileOptions {
    sourceCompatibility = pl.tretowicz.moviedb.Build.Version.java
    targetCompatibility = pl.tretowicz.moviedb.Build.Version.java
  }
  compileSdk = pl.tretowicz.moviedb.Build.Android.Sdk.compile

  defaultConfig {
    consumerProguardFiles(CONSUMER)
    minSdk = pl.tretowicz.moviedb.Build.Android.Sdk.min
  }
  namespace = baseAndroid

  flavorDimensions += listOf(ENV_DIMENSION)

  productFlavors {
    create(DEV) { dimension = ENV_DIMENSION }
    create(PROD) { dimension = ENV_DIMENSION }
  }
}


dependencies {
  implementation(project(":base"))
  implementation(project(":configuration"))

  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.browser)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  api(libs.timber)

  implementation(libs.firebase.crashlytics)
  implementation(libs.firebase.analytics.ktx)
  implementation(libs.firebase.remoteConfig)

  implementation(libs.moshi)
  implementation(libs.moshi.kotlin)
  kapt(libs.moshi.codegen)

  implementation(libs.accompanist.navigationAnimation)
  implementation(libs.androidx.navigation.fragmentKtx)
}
