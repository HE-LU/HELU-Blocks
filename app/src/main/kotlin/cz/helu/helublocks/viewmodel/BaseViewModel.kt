package cz.helu.helublocks.viewmodel

import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import cz.helu.helublocks.HeluBlocksApplication
import cz.helu.helublocks.event.ToastEvent
import cz.helu.helublocks.utility.CrashlyticsUtility
import org.alfonz.arch.AlfonzViewModel


abstract class BaseViewModel : AlfonzViewModel(), LifecycleObserver {
	init {
		CrashlyticsUtility.setCurrentViewModelKey(this::class.java.simpleName)
	}


	fun getApplicationContext(): Context {
		return HeluBlocksApplication.getContext()
	}


	fun handleError(message: String) {
		sendEvent(ToastEvent(message))
	}
}
