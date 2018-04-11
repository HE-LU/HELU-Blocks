package cz.helu.helublocks.viewmodel


import android.arch.lifecycle.LifecycleObserver
import android.databinding.ObservableInt
import android.os.Bundle
import org.alfonz.view.StatefulLayout


class MainViewModel() : BaseViewModel(), LifecycleObserver {
	val state = ObservableInt(StatefulLayout.CONTENT)

	constructor(extras: Bundle?) : this()
}