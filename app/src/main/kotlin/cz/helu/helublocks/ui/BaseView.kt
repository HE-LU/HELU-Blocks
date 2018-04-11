package cz.helu.helublocks.ui

import android.support.annotation.StringRes

import org.alfonz.arch.AlfonzView


interface BaseView : AlfonzView {
	fun showToast(@StringRes stringRes: Int)
	fun showToast(message: String)
	fun showSnackbar(@StringRes stringRes: Int)
	fun showSnackbar(message: String)
}