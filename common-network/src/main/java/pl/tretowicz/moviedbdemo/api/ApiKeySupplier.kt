package pl.tretowicz.moviedbdemo.api

import javax.inject.Inject

data class ApiKey @Inject constructor(
  val secret: String
)
