package cz.helu.helublocks.viewmodel


import android.arch.lifecycle.LifecycleObserver
import android.databinding.ObservableInt
import android.os.Bundle
import cz.helu.helublocks.entity.GameBlockPiece
import org.alfonz.view.StatefulLayout


class GameViewModel() : BaseViewModel(), LifecycleObserver {
	val COLUMN_COUNT = 8
	val state = ObservableInt(StatefulLayout.CONTENT)
	val board: Array<Array<GameBlockPiece?>> = Array(COLUMN_COUNT, { arrayOfNulls<GameBlockPiece>(COLUMN_COUNT) })


	constructor(extras: Bundle?) : this()
}