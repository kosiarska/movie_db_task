package pl.tretowicz.moviedbdemo.network.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresItemResponse(

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: Int
)
