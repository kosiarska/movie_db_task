package pl.tretowicz.moviedbdemo.domain.usecase.internal

import dagger.Reusable
import pl.tretowicz.moviedbdemo.domain.usecase.LikeOrUnlikeMovie
import pl.tretowicz.moviedbdemo.persistence.LikedMoviesPersistence
import pl.tretowicz.moviedbdemo.persistence.entity.LikedMovieEntity
import javax.inject.Inject

@Reusable
internal class LikeOrUnlikeMovieUseCase @Inject constructor(
  private val likedMoviesPersistence: LikedMoviesPersistence
) : LikeOrUnlikeMovie {

  override suspend fun invoke(id: Long) {
    likedMoviesPersistence.findLikedMovie(id)?.let {
      likedMoviesPersistence.deleteLikedMovie(it)
    } ?: run {
      likedMoviesPersistence.insertLikedMovie(
        LikedMovieEntity(
          id = id
        )
      )
    }
  }
}
