package pl.tretowicz.moviedbdemo.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object MoviesNetworkModule {

  @[Provides Singleton]
  fun moviesApi(retrofit: Retrofit): MoviesApi =
    retrofit.create(MoviesApi::class.java)
}
