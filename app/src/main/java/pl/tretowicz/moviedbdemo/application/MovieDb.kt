package pl.tretowicz.moviedbdemo.application

import android.app.Application
import pl.tretowicz.moviedbdemo.runtime.RunOnStartup
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieDb : Application() {

  @[Inject RunOnStartup]
  lateinit var tasks: Set<@JvmSuppressWildcards Runnable>

  override fun onCreate() {
    super.onCreate()
    tasks.forEach(Runnable::run)
  }
}
