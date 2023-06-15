package pl.tretowicz.moviedbdemo.domain.usecase

import pl.tretowicz.moviedbdemo.domain.model.MovieDetails

interface GetMovieDetails {
  suspend operator fun invoke(id: Long) : MovieDetails
}
