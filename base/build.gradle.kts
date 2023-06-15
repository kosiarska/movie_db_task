plugins {
  id("kotlin")
  kotlin("kapt")
}

dependencies {
  api(libs.kotlin.stdlib)
  implementation(libs.mapstruct)
  implementation(libs.bundles.coroutines)
}
