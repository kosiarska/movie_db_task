package pl.tretowicz.moviedbdemo.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.tretowicz.moviedbdemo.persistence.entity.LikedMovieEntity

@Database(
  version = 1,
  exportSchema = false,
  entities = [
    LikedMovieEntity::class
  ]
)
internal abstract class LikedMoviesDatabase : RoomDatabase() {
  abstract fun likedMovies(): LikedMoviesDao
}
