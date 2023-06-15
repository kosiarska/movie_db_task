package pl.tretowicz.moviedbdemo.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.tretowicz.moviedbdemo.domain.mapper.MoviesMapper
import pl.tretowicz.moviedbdemo.domain.model.Movie
import pl.tretowicz.moviedbdemo.domain.usecase.GetLikedMovies
import pl.tretowicz.moviedbdemo.network.MoviesApi
import timber.log.Timber

internal class MoviesPagingSource(
  private val moviesApi: MoviesApi,
  private val moviesMapper: MoviesMapper,
  private val getLikedMovies: GetLikedMovies
) : PagingSource<Int, Movie>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
    return try {
      val page = params.key ?: 1
      val response = moviesApi.getNowPlaying(page = page)

      val data = moviesMapper.mapNetwork(response.results)

      val likedMovies = getLikedMovies()
      data.forEach {
        it.isLiked = likedMovies.contains(it.id)
      }

      LoadResult.Page(
        data = data,
        prevKey = if (page == 1) null else page.minus(1),
        nextKey = if (response.results.isEmpty()) null else page.plus(1),
      )
    } catch (e: Exception) {
      Timber.e(e, "error in downloading movies")
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
    return null
  }
}
