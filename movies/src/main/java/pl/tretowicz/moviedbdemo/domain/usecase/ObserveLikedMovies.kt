package pl.tretowicz.moviedbdemo.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ObserveLikedMovies {
  operator fun invoke(): Flow<List<Long>>
}
