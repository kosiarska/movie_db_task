package pl.tretowicz.moviedbdemo.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyInterceptor @Inject constructor(
  private val apiKey: ApiKey
) : Interceptor {

  companion object {
    private const val SECRET_KEY = "Authorization"
  }

  override fun intercept(chain: Interceptor.Chain): Response =
    chain.request()
      .newBuilder()
      .addHeader(SECRET_KEY, "Bearer ${apiKey.secret}")
      .build()
      .let(chain::proceed)
}
