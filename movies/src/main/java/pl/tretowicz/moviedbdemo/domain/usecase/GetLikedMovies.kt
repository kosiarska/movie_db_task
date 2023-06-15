package pl.tretowicz.moviedbdemo.domain.usecase

interface GetLikedMovies {
  suspend operator fun invoke(): List<Long>
}
