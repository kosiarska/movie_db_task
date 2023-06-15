package pl.tretowicz.moviedbdemo.network.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguagesItemResponse(

	@Json(name="name")
	val name: String,

	@Json(name="iso_639_1")
	val iso6391: String,

	@Json(name="english_name")
	val englishName: String
)
