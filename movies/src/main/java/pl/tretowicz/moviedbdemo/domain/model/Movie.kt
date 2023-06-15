package pl.tretowicz.moviedbdemo.domain.model

data class Movie(

  val overview: String,

  val title: String,

  val posterPath: String?,

  val backdropPath: String?,

  val releaseDate: String,

  val id: Long,

  var isLiked: Boolean = false
)
