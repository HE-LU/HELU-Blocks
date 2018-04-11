package cz.helu.helublocks.extension

import android.util.Patterns


fun String.extParseFirstUrl(): String? {
	val matcher = Patterns.WEB_URL.matcher(this)
	return if (matcher.find()) matcher.group(0) else null
}