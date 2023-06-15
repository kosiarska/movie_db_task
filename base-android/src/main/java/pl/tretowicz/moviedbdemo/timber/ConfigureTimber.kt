package pl.tretowicz.moviedbdemo.timber

import pl.tretowicz.moviedbdemo.Configuration
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class ConfigureTimber @Inject constructor(
  private val configuration: Configuration,
  private val debug: DebugTree
) : Runnable {

  override fun run() {
    if (configuration.enableTimber()) {
      Timber.plant(debug)
    }
  }
}
