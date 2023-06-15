package pl.tretowicz.moviedbdemo.domain.usecase

import pl.tretowicz.moviedbdemo.domain.model.Movie

interface SearchMovies {
  suspend operator fun invoke(query: String): List<Movie>
}
