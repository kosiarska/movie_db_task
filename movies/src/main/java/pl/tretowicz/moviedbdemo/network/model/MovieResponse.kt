package pl.tretowicz.moviedbdemo.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

	@Json(name="overview")
	val overview: String,

	@Json(name="original_language")
	val originalLanguage: String,

	@Json(name="original_title")
	val originalTitle: String,

	@Json(name="video")
	val video: Boolean,

	@Json(name="title")
	val title: String,

	@Json(name="genre_ids")
	val genreIds: List<Int>,

	@Json(name="poster_path")
	val posterPath: String?,

	@Json(name="backdrop_path")
	val backdropPath: String?,

	@Json(name="release_date")
	val releaseDate: String,

	@Json(name="popularity")
	val popularity: Any,

	@Json(name="vote_average")
	val voteAverage: Any,

	@Json(name="id")
	val id: Long,

	@Json(name="adult")
	val adult: Boolean,

	@Json(name="vote_count")
	val voteCount: Int
)
