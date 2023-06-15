package pl.tretowicz.moviedbdemo.network.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountriesItemResponse(

	@Json(name="iso_3166_1")
	val iso31661: String,

	@Json(name="name")
	val name: String
)
