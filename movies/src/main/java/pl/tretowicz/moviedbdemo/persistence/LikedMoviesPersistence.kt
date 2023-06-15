package pl.tretowicz.moviedbdemo.persistence

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import pl.tretowicz.moviedbdemo.persistence.entity.LikedMovieEntity
import javax.inject.Inject

@Reusable
internal class LikedMoviesPersistence @Inject constructor(
  private val dao: LikedMoviesDao,
) {

  suspend fun findLikedMovie(id: Long): LikedMovieEntity? =
    dao.findLikedMovie(id)

  suspend fun insertLikedMovie(entity: LikedMovieEntity) =
    dao.insertLikedMovie(entity)

  suspend fun deleteLikedMovie(entity: LikedMovieEntity) =
    dao.deleteLikedMovie(entity)

  fun observeLikedMovies(): Flow<List<LikedMovieEntity>> =
    dao.observeLikedMovies()

  suspend fun getLikedMovies(): List<LikedMovieEntity> =
    dao.getLikedMovies()
}
