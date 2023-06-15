package pl.tretowicz.moviedbdemo.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.tretowicz.moviedbdemo.domain.usecase.CheckMovieLiked
import pl.tretowicz.moviedbdemo.domain.usecase.GetLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.GetMovieDetails
import pl.tretowicz.moviedbdemo.domain.usecase.LikeOrUnlikeMovie
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.SearchMovies
import pl.tretowicz.moviedbdemo.domain.usecase.internal.CheckMovieLikedUseCase
import pl.tretowicz.moviedbdemo.domain.usecase.internal.GetLikedMoviesUseCase
import pl.tretowicz.moviedbdemo.domain.usecase.internal.GetMovieDetailsUseCase
import pl.tretowicz.moviedbdemo.domain.usecase.internal.LikeOrUnlikeMovieUseCase
import pl.tretowicz.moviedbdemo.domain.usecase.internal.ObserveLikedMoviesUseCase
import pl.tretowicz.moviedbdemo.domain.usecase.internal.SearchMoviesUseCase

@[Module InstallIn(SingletonComponent::class)]
internal abstract class MoviesModule {

  @Binds
  abstract fun bindLikeOrUnlikeMovie(
    downloader: LikeOrUnlikeMovieUseCase): LikeOrUnlikeMovie

  @Binds
  abstract fun bindObserveLikedMovies(
    observer: ObserveLikedMoviesUseCase): ObserveLikedMovies

  @Binds
  abstract fun bindGetMovieDetails(
    observer: GetMovieDetailsUseCase): GetMovieDetails

  @Binds
  abstract fun bindCheckMovieLiked(
    observer: CheckMovieLikedUseCase): CheckMovieLiked

  @Binds
  abstract fun bindGetLikedMovies(
    downloader: GetLikedMoviesUseCase): GetLikedMovies

  @Binds
  abstract fun bindSearchMovies(
    downloader: SearchMoviesUseCase): SearchMovies
}
