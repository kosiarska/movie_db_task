package pl.tretowicz.moviedbdemo.domain.mapper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal class MoviesMapperModule {

  @[Provides Singleton]
  fun moviesMapper(): MoviesMapper =
    Mappers.getMapper(MoviesMapper::class.java)
}
