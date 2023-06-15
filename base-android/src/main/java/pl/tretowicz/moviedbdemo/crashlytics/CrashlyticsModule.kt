package pl.tretowicz.moviedbdemo.crashlytics

import pl.tretowicz.moviedbdemo.runtime.RunOnStartup
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@[Module InstallIn(SingletonComponent::class)]
abstract class CrashlyticsModule {

  @[Binds IntoSet RunOnStartup]
  abstract fun configureCrashlytics(task: ConfigureCrashlytics): Runnable
}
