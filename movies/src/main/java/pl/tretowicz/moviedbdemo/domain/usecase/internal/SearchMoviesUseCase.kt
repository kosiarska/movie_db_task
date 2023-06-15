package pl.tretowicz.moviedbdemo.domain.usecase.internal

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.tretowicz.moviedbdemo.domain.mapper.MoviesMapper
import pl.tretowicz.moviedbdemo.domain.model.Movie
import pl.tretowicz.moviedbdemo.domain.usecase.GetLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.SearchMovies
import pl.tretowicz.moviedbdemo.network.MoviesApi
import pl.tretowicz.moviedbdemo.persistence.LikedMoviesPersistence
import javax.inject.Inject

@Reusable
internal class SearchMoviesUseCase @Inject constructor(
  private val api: MoviesApi,
  private val moviesMapper: MoviesMapper
) : SearchMovies {

  override suspend fun invoke(query: String): List<Movie> =
    moviesMapper.mapNetwork(api.search(query).results)
}
