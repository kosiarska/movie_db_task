package pl.tretowicz.moviedbdemo

import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.Event
import com.google.firebase.analytics.FirebaseAnalytics.Param.SCREEN_NAME
import com.google.firebase.analytics.FirebaseAnalytics.Param.SEARCH_TERM
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Analytics @Inject constructor(
  private val analytics: FirebaseAnalytics
) {

  fun logSearchTermEvent(term: String) {
    if (term.isNotEmpty()) {
      bundleOf(SEARCH_TERM to term)
        .let { analytics.logEvent(Event.SEARCH, it) }
    }
  }

  fun logScreenEnter(screen: String) {
    if (screen.isNotEmpty()) {
      bundleOf(SCREEN_NAME to screen)
        .let { analytics.logEvent(Event.SCREEN_VIEW, it) }
    }
  }

  companion object {
    const val CONTACT_US = "Contact_Us"
  }
}
