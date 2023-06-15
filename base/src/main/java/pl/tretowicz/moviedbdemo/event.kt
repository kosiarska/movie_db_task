package pl.tretowicz.moviedbdemo

interface Event

/**
 * Defines common event designated for redirecting user
 * to the previous screen.
 */
object NavigateUp : Event

/**
 * Defines common event designated for redirecting user
 * to the browser [title] is an optional text displayed when
 * chooser will appear.
 */
data class NavigateToBrowser(
  val title: String = "",
  val url: String
) : Event
