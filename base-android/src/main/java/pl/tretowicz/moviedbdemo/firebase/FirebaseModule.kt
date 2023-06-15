package pl.tretowicz.moviedbdemo.firebase

import android.annotation.SuppressLint
import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object FirebaseModule {

  @[Provides Singleton]
  internal fun crashlytics(): FirebaseCrashlytics {
    return FirebaseCrashlytics.getInstance()
  }

  @SuppressLint("MissingPermission")
  @[Provides Singleton]
  internal fun analytics(@ApplicationContext context: Context): FirebaseAnalytics {
    return FirebaseAnalytics.getInstance(context)
  }
}
