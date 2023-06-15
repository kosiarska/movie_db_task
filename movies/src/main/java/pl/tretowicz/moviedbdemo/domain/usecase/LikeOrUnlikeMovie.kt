package pl.tretowicz.moviedbdemo.domain.usecase

interface LikeOrUnlikeMovie {
  suspend operator fun invoke(id: Long)
}
