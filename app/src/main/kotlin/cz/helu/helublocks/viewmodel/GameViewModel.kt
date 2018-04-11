package cz.helu.helublocks.viewmodel


import android.arch.lifecycle.LifecycleObserver
import android.databinding.ObservableInt
import android.os.Bundle
import cz.helu.helublocks.HeluBlocksConfig.COLUMN_COUNT
import cz.helu.helublocks.entity.GameBlock
import cz.helu.helublocks.entity.GameBlockPiece
import org.alfonz.view.StatefulLayout


class GameViewModel() : BaseViewModel(), LifecycleObserver {
	val state = ObservableInt(StatefulLayout.CONTENT)
	val board: Array<Array<GameBlockPiece?>> = Array(COLUMN_COUNT, { arrayOfNulls<GameBlockPiece>(COLUMN_COUNT) })
	val score = ObservableInt(0)


	constructor(extras: Bundle?) : this()


	fun setGameBlock(block: GameBlock, posX: Int, posY: Int): Boolean {
		if (block.blockSize == 2) { // TT 2x2
			// Tt First just check if we can put down the block
			for (x in 0 until block.blockSize) {
				for (y in 0 until block.blockSize) {
					if (block.blockPieces[y][x] != null) {
						if (posY + y < 0 || posY + y >= COLUMN_COUNT || posX + x < 0 || posX + x >= COLUMN_COUNT)
							return false
						if (block.blockPieces[y][x] != null && board[posY + y][posX + x] != null)
							return false
					}
				}
			}

			// Tt Now we can safely save the block into board
			for (x in 0 until block.blockSize) {
				for (y in 0 until block.blockSize) {
					if (block.blockPieces[y][x] != null)
						board[posY + y][posX + x] = block.blockPieces[y][x]
				}
			}

			checkFilled()
		} else { // TT 3x3
			// Tt First just check if we can put down the block
			for (x in 0 until block.blockSize) {
				for (y in 0 until block.blockSize) {
					if (block.blockPieces[y][x] != null) {
						if (posY + y < 0 - 1 || posY + y > COLUMN_COUNT || posX + x - 1 < 0 || posX + x > COLUMN_COUNT)
							return false
						if (block.blockPieces[y][x] != null && board[posY + y - 1][posX + x - 1] != null)
							return false
					}
				}
			}

			// Tt Now we can safely save the block into board
			for (x in 0 until block.blockSize) {
				for (y in 0 until block.blockSize) {
					if (block.blockPieces[y][x] != null)
						board[posY + y - 1][posX + x - 1] = block.blockPieces[y][x]
				}
			}

			checkFilled()
		}
		return true
	}


	private fun checkFilled() {
		for (x in 0 until COLUMN_COUNT) {
			var isFilled = true
			for (y in 0 until COLUMN_COUNT) {
				if (board[y][x] == null)
					isFilled = false
			}
			if (isFilled) {
				// TT Add Score and clear the row!
				score.set(score.get() + 100)
				for (y in 0 until COLUMN_COUNT) {
					board[y][x] = null
				}
			}
		}

		for (x in 0 until COLUMN_COUNT) {
			var isFilled = true
			for (y in 0 until COLUMN_COUNT) {
				if (board[x][y] == null)
					isFilled = false
			}
			if (isFilled) {
				// TT Add Score and clear the row!
				score.set(score.get() + 100)
				for (y in 0 until COLUMN_COUNT) {
					board[x][y] = null
				}
			}
		}
	}
}