@Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.kapt) apply false
}

buildscript {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
  dependencies {
    classpath(libs.hilt.gradlePlugin)
    classpath(libs.google.services.gradlePlugin)
    classpath(libs.firebase.crashlytics.gradlePlugin)
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
