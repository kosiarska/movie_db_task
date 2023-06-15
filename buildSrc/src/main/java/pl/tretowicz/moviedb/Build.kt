package pl.tretowicz.moviedb

import org.gradle.api.JavaVersion

object Build {

  object Android {
    object Sdk {
      const val compile = 33
      const val min = 23
      const val target = 33
    }
  }

  object Version {
    val java = JavaVersion.VERSION_17
    const val kotlin = "11"
  }
}
