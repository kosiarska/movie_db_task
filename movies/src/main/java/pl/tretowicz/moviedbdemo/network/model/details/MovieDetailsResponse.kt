package pl.tretowicz.moviedbdemo.network.model.details

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(

	@Json(name="original_language")
	val originalLanguage: String,

	@Json(name="imdb_id")
	val imdbId: String,

	@Json(name="video")
	val video: Boolean,

	@Json(name="title")
	val title: String,

	@Json(name="backdrop_path")
	val backdropPath: String?,

	@Json(name="revenue")
	val revenue: Any,

	@Json(name="genres")
	val genres: List<GenresItemResponse>,

	@Json(name="popularity")
	val popularity: Any,

	@Json(name="production_countries")
	val productionCountries: List<ProductionCountriesItemResponse>,

	@Json(name="id")
	val id: Long,

	@Json(name="vote_count")
	val voteCount: Int,

	@Json(name="budget")
	val budget: Int,

	@Json(name="overview")
	val overview: String,

	@Json(name="original_title")
	val originalTitle: String,

	@Json(name="runtime")
	val runtime: Int,

	@Json(name="poster_path")
	val posterPath: String?,

	@Json(name="spoken_languages")
	val spokenLanguages: List<SpokenLanguagesItemResponse>,

	@Json(name="production_companies")
	val productionCompanies: List<ProductionCompaniesItemResponse>,

	@Json(name="release_date")
	val releaseDate: String,

	@Json(name="vote_average")
	val voteAverage: Any,

	@Json(name="belongs_to_collection")
	val belongsToCollection: BelongsToCollectionResponse?,

	@Json(name="tagline")
	val tagline: String,

	@Json(name="adult")
	val adult: Boolean,

	@Json(name="homepage")
	val homepage: String,

	@Json(name="status")
	val status: String
)







