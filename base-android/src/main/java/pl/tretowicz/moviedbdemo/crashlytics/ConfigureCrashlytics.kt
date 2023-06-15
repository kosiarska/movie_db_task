package pl.tretowicz.moviedbdemo.crashlytics

import pl.tretowicz.moviedbdemo.Configuration
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

class ConfigureCrashlytics @Inject constructor(
  private val configuration: Configuration,
  private val crashlytics: FirebaseCrashlytics
) : Runnable {

  override fun run() {
    crashlytics
      .setCrashlyticsCollectionEnabled(configuration.enableCrashlytics())
  }
}
