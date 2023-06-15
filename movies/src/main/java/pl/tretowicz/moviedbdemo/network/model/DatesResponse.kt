package pl.tretowicz.moviedbdemo.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatesResponse(

	@Json(name="maximum")
	val maximum: String,

	@Json(name="minimum")
	val minimum: String
)
