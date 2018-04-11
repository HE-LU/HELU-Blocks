package cz.helu.helublocks.fragment

import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import cz.helu.helublocks.event.SnackbarEvent
import cz.helu.helublocks.event.ToastEvent
import cz.helu.helublocks.extension.extGetString
import cz.helu.helublocks.ui.BaseView
import cz.helu.helublocks.utility.CrashlyticsUtility
import cz.helu.helublocks.viewmodel.BaseViewModel
import org.alfonz.arch.AlfonzBindingFragment
import org.alfonz.arch.event.EventObserver


abstract class BaseBindingFragment<T : BaseViewModel, B : ViewDataBinding> : AlfonzBindingFragment<T, B>(), BaseView {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		CrashlyticsUtility.setCurrentFragmentKey(this::class.java.simpleName)
		setHasOptionsMenu(true)
		setupObservers()
	}


	override fun onResume() {
		CrashlyticsUtility.setCurrentFragmentKey(this::class.java.simpleName)
		super.onResume()
	}


	override fun getContext(): Context {
		return super.getContext()!!
	}


	override fun showToast(message: String) {
		Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
	}


	override fun showSnackbar(message: String) {
		if (view != null) {
			Snackbar.make(view!!, message, Snackbar.LENGTH_LONG).show()
		}
	}


	private fun setupObservers() {
		viewModel.observeEvent(this, ToastEvent::class.java, EventObserver { toastEvent -> showToast(toastEvent.message) })
		viewModel.observeEvent(this, SnackbarEvent::class.java, EventObserver { snackbarEvent -> showSnackbar(snackbarEvent.message) })
	}


	override fun showToast(stringRes: Int) {
		showToast(stringRes.extGetString())
	}


	override fun showSnackbar(stringRes: Int) {
		showSnackbar(stringRes.extGetString())
	}


	override fun onSaveInstanceState(outState: Bundle) {
		try {
			super.onSaveInstanceState(outState)
		} catch (e: Exception) {
			Crashlytics.log(1, "FRAGMENT_SAVE_INSTANCE_STATE", "Fragment SaveInstanceState crash: ${this.javaClass}\n$e")
		}
	}


	fun getExtras(): Bundle? {
		return activity?.intent?.extras
	}


	/**
	 * Could handle back press.
	 * @return true if back press was handled
	 */
	open fun onBackPressed(): Boolean {
		return false
	}
}
