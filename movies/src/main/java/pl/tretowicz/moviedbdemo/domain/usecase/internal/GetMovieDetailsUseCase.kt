package pl.tretowicz.moviedbdemo.domain.usecase.internal

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.ResponseBody
import pl.tretowicz.moviedbdemo.domain.mapper.MoviesMapper
import pl.tretowicz.moviedbdemo.domain.model.MovieDetails
import pl.tretowicz.moviedbdemo.domain.usecase.GetMovieDetails
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.network.MoviesApi
import pl.tretowicz.moviedbdemo.persistence.LikedMoviesPersistence
import javax.inject.Inject

@Reusable
internal class GetMovieDetailsUseCase @Inject constructor(
  private val moviesApi: MoviesApi,
  private val moviesMapper: MoviesMapper
) : GetMovieDetails {

  override suspend fun invoke(id: Long): MovieDetails =
    moviesMapper.mapDetailsNetwork(moviesApi.getMovieDetails(movieId = id))
}
