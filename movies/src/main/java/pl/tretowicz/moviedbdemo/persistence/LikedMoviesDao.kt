package pl.tretowicz.moviedbdemo.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.tretowicz.moviedbdemo.persistence.entity.LikedMovieEntity

@Dao
internal abstract class LikedMoviesDao {

  @Query("SELECT * FROM liked_movie WHERE id LIKE :id LIMIT 1")
  abstract suspend fun findLikedMovie(id: Long): LikedMovieEntity?

  @Query("SELECT * FROM liked_movie")
  abstract fun observeLikedMovies(): Flow<List<LikedMovieEntity>>

  @Query("SELECT * FROM liked_movie")
  abstract suspend fun getLikedMovies(): List<LikedMovieEntity>

  @Insert(onConflict = REPLACE)
  abstract suspend fun insertLikedMovie(entity: LikedMovieEntity)

  @Delete
  abstract suspend fun deleteLikedMovie(entity: LikedMovieEntity)
}
