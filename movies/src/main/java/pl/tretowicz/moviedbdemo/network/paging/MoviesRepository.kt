package pl.tretowicz.moviedbdemo.network.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import pl.tretowicz.moviedbdemo.domain.mapper.MoviesMapper
import pl.tretowicz.moviedbdemo.domain.usecase.GetLikedMovies
import pl.tretowicz.moviedbdemo.network.MoviesApi
import javax.inject.Inject

class MoviesRepository @Inject constructor(
  private val moviesApi: MoviesApi,
  private val moviesMapper: MoviesMapper,
  private val getLikedMovies: GetLikedMovies
) {
  fun getMovies() = Pager(
    config = PagingConfig(
      pageSize = PAGE_SIZE,
      enablePlaceholders = false
    ),
    pagingSourceFactory = {
      MoviesPagingSource(
        moviesApi = moviesApi,
        moviesMapper = moviesMapper,
        getLikedMovies = getLikedMovies
      )
    }
  ).flow

  private companion object {
    const val PAGE_SIZE = 20
  }
}
