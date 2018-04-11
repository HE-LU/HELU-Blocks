package cz.helu.helublocks.extension

import android.databinding.Observable


fun Observable.extAddOnPropertyChangedCallback(listener: (Observable?, Int, Observable.OnPropertyChangedCallback) -> Unit): Observable.OnPropertyChangedCallback {
	val callback = object : Observable.OnPropertyChangedCallback() {
		override fun onPropertyChanged(p0: Observable?, p1: Int) {
			return listener.invoke(p0, p1, this)
		}
	}
	addOnPropertyChangedCallback(callback)
	return callback
}