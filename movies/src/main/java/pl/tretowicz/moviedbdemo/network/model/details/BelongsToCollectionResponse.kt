package pl.tretowicz.moviedbdemo.network.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BelongsToCollectionResponse(

	@Json(name="backdrop_path")
	val backdropPath: String?,

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: Int,

	@Json(name="poster_path")
	val posterPath: String?
)
