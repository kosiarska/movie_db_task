package pl.tretowicz.moviedbdemo.timber

import pl.tretowicz.moviedbdemo.runtime.RunOnStartup
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import timber.log.Timber.DebugTree
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object TimberModule {

  @[Provides IntoSet RunOnStartup]
  internal fun configureTimber(task: ConfigureTimber): Runnable = task

  @[Provides Singleton]
  internal fun debugTree(): DebugTree = DebugTree()
}
