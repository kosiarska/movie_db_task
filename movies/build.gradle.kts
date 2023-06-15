import com.android.build.gradle.ProguardFiles.ProguardFile.OPTIMIZE
import pl.tretowicz.moviedb.Application.Flavour.DEV
import pl.tretowicz.moviedb.Application.Flavour.ENV_DIMENSION
import pl.tretowicz.moviedb.Application.Flavour.PROD
import pl.tretowicz.moviedb.Application.Package.movies
import pl.tretowicz.moviedb.Build
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
    buildConfig = false
  }

  buildTypes {
    release {
      proguardFiles(getDefaultProguardFile(OPTIMIZE.fileName), RULES)
    }
  }

  compileOptions {
    sourceCompatibility = Build.Version.java
    targetCompatibility = Build.Version.java
  }
  compileSdk = Build.Android.Sdk.compile

  defaultConfig {
    consumerProguardFiles(CONSUMER)
    minSdk = Build.Android.Sdk.min
    namespace = movies
  }

  flavorDimensions += listOf(ENV_DIMENSION)

  productFlavors {
    create(DEV) { dimension = ENV_DIMENSION }
    create(PROD) { dimension = ENV_DIMENSION }
  }
}

dependencies {

  implementation(project(":base"))
  implementation(project(":base-android"))
  implementation(project(":common-network"))

  implementation(libs.hilt.android)
  implementation("androidx.paging:paging-common-ktx:3.1.1")
  kapt(libs.hilt.compiler)

  implementation(libs.mapstruct)
  kapt(libs.mapstruct.processor)

  implementation(libs.moshi)
  kapt(libs.moshi.codegen)

  api(libs.bundles.room)
  kapt(libs.androidx.room.compiler)
}
