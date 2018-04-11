package cz.helu.helublocks.extension

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment


@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(crossinline provider: () -> VM): VM {
	object : ViewModelProvider.Factory {
		override fun <T : ViewModel> create(modelClass: Class<T>) = provider() as T
	}.let {
		return ViewModelProviders.of(this, it).get(VM::class.java)
	}
}