package pl.tretowicz.moviedbdemo.domain.model

data class MovieDetails(

  val title: String,

  val backdropPath: String?,

  val popularity: Any,

  val id: Long,

  val voteCount: Int,

  val budget: Int,

  val overview: String,

  val originalTitle: String,

  val runtime: Int,

  val posterPath: String?,

  val releaseDate: String,

  val voteAverage: Any,

  val tagline: String,

  val adult: Boolean,

  val homepage: String,

  val status: String
)
