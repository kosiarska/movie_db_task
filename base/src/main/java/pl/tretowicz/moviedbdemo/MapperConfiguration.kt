package pl.tretowicz.moviedbdemo

import org.mapstruct.MapperConfig
import org.mapstruct.NullValueCheckStrategy.ALWAYS
import org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT
import org.mapstruct.ReportingPolicy.IGNORE

@MapperConfig(
  nullValueCheckStrategy = ALWAYS,
  nullValueMappingStrategy = RETURN_DEFAULT,
  unmappedTargetPolicy = IGNORE
)
interface MapperConfiguration
