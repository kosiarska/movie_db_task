package pl.tretowicz.moviedbdemo.network.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class NowPlayingResponse(

	@Json(name="dates")
	val dates: DatesResponse,

	@Json(name="page")
	val page: Int,

	@Json(name="total_pages")
	val totalPages: Int,

	@Json(name="results")
	val results: List<MovieResponse>,

	@Json(name="total_results")
	val totalResults: Int
)
