package pl.tretowicz.moviedbdemo.runtime

import javax.inject.Qualifier

/**
 * Denotes runnable that should be executed on application startup.
 *
 */
@[Qualifier Retention]
annotation class RunOnStartup
