package pl.tretowicz.moviedbdemo.network.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompaniesItemResponse(

	@Json(name="logo_path")
	val logoPath: String?,

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: Int,

	@Json(name="origin_country")
	val originCountry: String
)
