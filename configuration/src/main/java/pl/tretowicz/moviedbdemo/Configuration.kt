package pl.tretowicz.moviedbdemo

import dagger.Reusable
import pl.tretowicz.moviedb.configuration.BuildConfig
import javax.inject.Inject

@Reusable
class Configuration @Inject constructor() {

  fun enableTimber(): Boolean =
    BuildConfig.ENABLE_TIMBER

  fun apiConnectionTimeout(): Long =
    BuildConfig.API_CONNECTION_TIMEOUT

  fun apiReadTimeout(): Long =
    BuildConfig.API_READ_TIMEOUT

  fun apiUrl(): String =
    BuildConfig.API_URL

  fun apiSecret(): String =
    BuildConfig.API_SECRET

  fun apiWriteTimeout(): Long =
    BuildConfig.API_WRITE_TIMEOUT

  fun isDebug(): Boolean =
    BuildConfig.DEBUG

  fun enableCrashlytics(): Boolean =
    BuildConfig.ENABLE_CRASHLYTICS

  fun logLevel(): String =
    BuildConfig.LOG_LEVEL
}
