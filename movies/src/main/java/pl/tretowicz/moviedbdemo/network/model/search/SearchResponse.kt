package pl.tretowicz.moviedbdemo.network.model.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.tretowicz.moviedbdemo.network.model.MovieResponse

@JsonClass(generateAdapter = true)
data class SearchResponse(

  @Json(name="page")
  val page: Int,

  @Json(name="results")
  val results: List<MovieResponse>,
)
