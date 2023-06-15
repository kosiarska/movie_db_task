package pl.tretowicz.moviedbdemo

import pl.tretowicz.moviedbdemo.api.ApiKey
import pl.tretowicz.moviedbdemo.api.ApiKeyInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import pl.tretowicz.moviedb.configuration.BuildConfig
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {

  @[Provides Singleton]
  internal fun apiKey(
    config: Configuration
  ): ApiKey =
    ApiKey(
      secret = config.apiSecret()
    )

  @[Provides Singleton]
  internal fun retrofit(
    client: OkHttpClient,
    config: Configuration,
    converterFactory: Converter.Factory
  ): Retrofit =
    Retrofit.Builder()
      .addConverterFactory(converterFactory)
      .baseUrl(config.apiUrl())
      .client(client)
      .validateEagerly(BuildConfig.DEBUG)
      .build()

  @[Provides Singleton]
  internal fun client(
    apiKey: ApiKeyInterceptor,
    config: Configuration,
    logging: HttpLoggingInterceptor,
  ): OkHttpClient =
    OkHttpClient.Builder()
      .addInterceptor(apiKey)
      .addInterceptor(logging)
      .connectTimeout(config.apiConnectionTimeout(), SECONDS)
      .readTimeout(config.apiReadTimeout(), SECONDS)
      .writeTimeout(config.apiWriteTimeout(), SECONDS).build()

  @[Provides Singleton]
  internal fun converterFactory(moshi: Moshi): Converter.Factory =
    MoshiConverterFactory.create(moshi).asLenient()

  @[Provides Singleton]
  internal fun loggingInterceptor(level: Level): HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(level)

  @[Provides Singleton]
  internal fun loggingLevel(config: Configuration): Level =
    Level.valueOf(config.logLevel().trim().uppercase())

  @[Provides Singleton]
  internal fun moshi(): Moshi =
    Moshi.Builder()
      .addLast(KotlinJsonAdapterFactory())
      .build()
}
