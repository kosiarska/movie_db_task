package pl.tretowicz.moviedbdemo.domain.mapper

import org.mapstruct.Mapper
import pl.tretowicz.moviedbdemo.MapperConfiguration
import pl.tretowicz.moviedbdemo.domain.model.Movie
import pl.tretowicz.moviedbdemo.domain.model.MovieDetails
import pl.tretowicz.moviedbdemo.network.model.details.MovieDetailsResponse
import pl.tretowicz.moviedbdemo.network.model.MovieResponse

@Mapper(
  config = MapperConfiguration::class,
)
abstract class MoviesMapper {

  abstract fun mapNetwork(result: List<MovieResponse>): List<Movie>

  abstract fun mapDetailsNetwork(result: MovieDetailsResponse): MovieDetails
}
