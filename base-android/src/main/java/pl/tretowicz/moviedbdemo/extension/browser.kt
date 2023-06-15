package pl.tretowicz.moviedbdemo.extension

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.createChooser
import android.net.Uri
import android.webkit.URLUtil.isHttpUrl
import android.webkit.URLUtil.isHttpsUrl

private const val HTTPS_PROTOCOL = "https://"

fun Context.openWebBrowser(title: String, url: String) {

  val content =
    when (isHttpUrl(url) or isHttpsUrl(url)) {
      true -> url
      false -> "$HTTPS_PROTOCOL$url"
    }

  Intent(ACTION_VIEW, Uri.parse(content))
    .run { startActivity(createChooser(this, title)) }
}
