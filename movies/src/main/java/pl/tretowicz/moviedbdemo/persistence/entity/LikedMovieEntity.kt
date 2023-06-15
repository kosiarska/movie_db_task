package pl.tretowicz.moviedbdemo.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
  tableName = "liked_movie",
)
internal data class LikedMovieEntity(

  @[PrimaryKey ColumnInfo(name = "id")]
  var id: Long,
)
