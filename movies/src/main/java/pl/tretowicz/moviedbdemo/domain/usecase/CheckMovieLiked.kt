package pl.tretowicz.moviedbdemo.domain.usecase

interface CheckMovieLiked {
  suspend operator fun invoke(id: Long) : Boolean
}
