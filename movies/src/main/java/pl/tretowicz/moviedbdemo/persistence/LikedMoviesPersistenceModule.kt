package pl.tretowicz.moviedbdemo.persistence

import android.content.Context
import androidx.room.Room.databaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object LikedMoviesPersistenceModule {

  private const val LIKED_MOVIES_DB = "liked_movies.db"

  @[Provides Singleton]
  fun database(@ApplicationContext context: Context): LikedMoviesDatabase =
    databaseBuilder(context, LikedMoviesDatabase::class.java, LIKED_MOVIES_DB)
      .fallbackToDestructiveMigration()
      .build()

  @[Provides Singleton]
  fun likedMovies(database: LikedMoviesDatabase): LikedMoviesDao =
    database.likedMovies()
}
