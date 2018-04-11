package cz.helu.helublocks.viewmodel


import android.arch.lifecycle.LifecycleObserver
import android.databinding.ObservableInt
import android.os.Bundle
import cz.helu.helublocks.entity.GameBlock
import cz.helu.helublocks.entity.GameBlockPiece
import org.alfonz.view.StatefulLayout


class GameViewModel() : BaseViewModel(), LifecycleObserver {
	val COLUMN_COUNT = 8
	val state = ObservableInt(StatefulLayout.CONTENT)
	val board: Array<Array<GameBlockPiece?>> = Array(COLUMN_COUNT, { arrayOfNulls<GameBlockPiece>(COLUMN_COUNT) })


	constructor(extras: Bundle?) : this()


	fun setGameBlock(block: GameBlock, posX: Int, posY: Int): Boolean {
		if (block.blockSize == 2) { // TT 2x2
			for (x in 0 until block.blockSize) {
				for (y in 0 until block.blockSize) {
					board[posY + y][posX + x] = block.blockPieces[y][x]
				}
			}
		} else { // TT 3x3
			for (x in 0 until block.blockSize) {
				for (y in 0 until block.blockSize) {
					board[posY + y - 1][posX + x - 1] = block.blockPieces[y][x]
				}
			}
		}
		return true
	}
}