package pl.tretowicz.moviedbdemo.network

import pl.tretowicz.moviedbdemo.network.model.NowPlayingResponse
import pl.tretowicz.moviedbdemo.network.model.details.MovieDetailsResponse
import pl.tretowicz.moviedbdemo.network.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

  @GET("movie/now_playing")
  suspend fun getNowPlaying(
    @Query("language") language: String = "pl-PL",
    @Query("page") page: Int = 1
  ): NowPlayingResponse

  @GET("movie/{movie_id}")
  suspend fun getMovieDetails(
    @Path("movie_id") movieId: Long,
    @Query("language") language: String = "pl-PL"
  ): MovieDetailsResponse

  @GET("search/movie")
  suspend fun search(
    @Query("query") query: String,
    @Query("language") language: String = "pl-PL"
  ): SearchResponse
}
