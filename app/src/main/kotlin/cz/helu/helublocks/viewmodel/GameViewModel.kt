package cz.helu.helublocks.viewmodel


import android.arch.lifecycle.LifecycleObserver
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.os.Bundle
import cz.helu.helublocks.HeluBlocksConfig
import cz.helu.helublocks.HeluBlocksConfig.COLUMN_COUNT
import cz.helu.helublocks.entity.GameBlock
import cz.helu.helublocks.entity.GameBlockPiece
import org.alfonz.utility.Logcat
import org.alfonz.view.StatefulLayout


class GameViewModel() : BaseViewModel(), LifecycleObserver {
	val state = ObservableInt(StatefulLayout.CONTENT)
	val board: Array<Array<GameBlockPiece?>> = Array(COLUMN_COUNT, { arrayOfNulls<GameBlockPiece>(COLUMN_COUNT) })
	val score = ObservableInt(0)
	val gameOver = ObservableBoolean(false)
	private val availableBlocks: MutableList<GameBlock> = ArrayList()


	constructor(extras: Bundle?) : this()


	fun setGameBlock(block: GameBlock, posX: Int, posY: Int): Boolean {
		if (canBlockBePlaced(block, posX, posY)) {
			if (block.blockSize == 2) { // TT 2x2
				// Tt Now we can safely save the block into board
				for (x in 0 until block.blockSize) {
					for (y in 0 until block.blockSize) {
						if (block.blockPieces[x][y] != null)
							board[posX + x][posY + y] = block.blockPieces[x][y]
					}
				}
			} else { // TT 3x3
				// Tt Now we can safely save the block into board
				for (x in 0 until block.blockSize) {
					for (y in 0 until block.blockSize) {
						if (block.blockPieces[x][y] != null)
							board[posX + x - 1][posY + y - 1] = block.blockPieces[x][y]
					}
				}
			}

			checkFilled()
		}
		return true
	}


	fun getNewGameBlock(): GameBlock {
		val gameBlock = GameBlock.createRandomBlock()
		availableBlocks.add(gameBlock)
		return gameBlock
	}


	fun removeBlock(gameBlock: GameBlock) {
		availableBlocks.remove(gameBlock)
	}


	fun checkGameOver() {
		printBoard()
		availableBlocks.forEach { block ->
			printBlock(block)
			for (x in 0 until HeluBlocksConfig.COLUMN_COUNT) {
				for (y in 0 until HeluBlocksConfig.COLUMN_COUNT) {
					if (board[x][y] == null) {
						if (canBlockBePlaced(block, x, y)) {
							gameOver.set(false)
							return
						}
					}
				}
			}
		}

		gameOver.set(true)
	}


	private fun checkFilled() {
		// TODO: Remove line and row at the same time!
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
	}


	private fun canBlockBePlaced(block: GameBlock, posX: Int, posY: Int): Boolean {
		if (block.blockSize == 2) { // TT 2x2
			// Tt First just check if we can put down the block
			for (x in 0 until block.blockSize) {
				for (y in 0 until block.blockSize) {
					if (block.blockPieces[x][y] != null) {
						if (posX + x < 0 || posX + x >= COLUMN_COUNT || posY + y < 0 || posY + y >= COLUMN_COUNT)
							return false
						if (block.blockPieces[x][y] != null && board[posX + x][posY + y] != null)
							return false
					}
				}
			}
		} else { // TT 3x3
			// Tt First just check if we can put down the block
			for (x in 0 until block.blockSize) {
				for (y in 0 until block.blockSize) {
					if (block.blockPieces[x][y] != null) {
						if (posX + x - 1 < 0 || posX + x > COLUMN_COUNT || posY + y - 1 < 0 || posY + y > COLUMN_COUNT)
							return false
						if (block.blockPieces[x][y] != null && board[posX + x - 1][posY + y - 1] != null)
							return false
					}
				}
			}
		}
		return true
	}


	private fun printBlock(block: GameBlock) {
		Logcat.e("Play Block:\n" +
				"┏━━━┳━━━┳━━━┓\n" +
				"┃ ${if (block.blockPieces[0][0] != null) "█" else " "} ┃ ${if (block.blockPieces[0][1] != null) "█" else " "} ┃ ${if (block.blockPieces[0][2] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━┫\n" +
				"┃ ${if (block.blockPieces[1][0] != null) "█" else " "} ┃ ${if (block.blockPieces[1][1] != null) "█" else " "} ┃ ${if (block.blockPieces[1][2] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━┫\n" +
				"┃ ${if (block.blockPieces[2][0] != null) "█" else " "} ┃ ${if (block.blockPieces[2][1] != null) "█" else " "} ┃ ${if (block.blockPieces[2][2] != null) "█" else " "} ┃\n" +
				"┗━━━┻━━━┻━━━┛\n")
	}


	private fun printBoard() {
		Logcat.e("Play Board:\n" +
				"┏━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┓\n" +
				"┃ ${if (board[0][0] != null) "█" else " "} ┃ ${if (board[0][1] != null) "█" else " "} ┃ ${if (board[0][2] != null) "█" else " "} ┃ ${if (board[0][3] != null) "█" else " "} ┃ ${if (board[0][4] != null) "█" else " "} ┃ ${if (board[0][5] != null) "█" else " "} ┃ ${if (board[0][6] != null) "█" else " "} ┃ ${if (board[0][7] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫\n" +
				"┃ ${if (board[1][0] != null) "█" else " "} ┃ ${if (board[1][1] != null) "█" else " "} ┃ ${if (board[1][2] != null) "█" else " "} ┃ ${if (board[1][3] != null) "█" else " "} ┃ ${if (board[1][4] != null) "█" else " "} ┃ ${if (board[1][5] != null) "█" else " "} ┃ ${if (board[1][6] != null) "█" else " "} ┃ ${if (board[1][7] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫\n" +
				"┃ ${if (board[2][0] != null) "█" else " "} ┃ ${if (board[2][1] != null) "█" else " "} ┃ ${if (board[2][2] != null) "█" else " "} ┃ ${if (board[2][3] != null) "█" else " "} ┃ ${if (board[2][4] != null) "█" else " "} ┃ ${if (board[2][5] != null) "█" else " "} ┃ ${if (board[2][6] != null) "█" else " "} ┃ ${if (board[2][7] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫\n" +
				"┃ ${if (board[3][0] != null) "█" else " "} ┃ ${if (board[3][1] != null) "█" else " "} ┃ ${if (board[3][2] != null) "█" else " "} ┃ ${if (board[3][3] != null) "█" else " "} ┃ ${if (board[3][4] != null) "█" else " "} ┃ ${if (board[3][5] != null) "█" else " "} ┃ ${if (board[3][6] != null) "█" else " "} ┃ ${if (board[3][7] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫\n" +
				"┃ ${if (board[4][0] != null) "█" else " "} ┃ ${if (board[4][1] != null) "█" else " "} ┃ ${if (board[4][2] != null) "█" else " "} ┃ ${if (board[4][3] != null) "█" else " "} ┃ ${if (board[4][4] != null) "█" else " "} ┃ ${if (board[4][5] != null) "█" else " "} ┃ ${if (board[4][6] != null) "█" else " "} ┃ ${if (board[4][7] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫\n" +
				"┃ ${if (board[5][0] != null) "█" else " "} ┃ ${if (board[5][1] != null) "█" else " "} ┃ ${if (board[5][2] != null) "█" else " "} ┃ ${if (board[5][3] != null) "█" else " "} ┃ ${if (board[5][4] != null) "█" else " "} ┃ ${if (board[5][5] != null) "█" else " "} ┃ ${if (board[5][6] != null) "█" else " "} ┃ ${if (board[5][7] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫\n" +
				"┃ ${if (board[6][0] != null) "█" else " "} ┃ ${if (board[6][1] != null) "█" else " "} ┃ ${if (board[6][2] != null) "█" else " "} ┃ ${if (board[6][3] != null) "█" else " "} ┃ ${if (board[6][4] != null) "█" else " "} ┃ ${if (board[6][5] != null) "█" else " "} ┃ ${if (board[6][6] != null) "█" else " "} ┃ ${if (board[6][7] != null) "█" else " "} ┃\n" +
				"┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫\n" +
				"┃ ${if (board[7][0] != null) "█" else " "} ┃ ${if (board[7][1] != null) "█" else " "} ┃ ${if (board[7][2] != null) "█" else " "} ┃ ${if (board[7][3] != null) "█" else " "} ┃ ${if (board[7][4] != null) "█" else " "} ┃ ${if (board[7][5] != null) "█" else " "} ┃ ${if (board[7][6] != null) "█" else " "} ┃ ${if (board[7][7] != null) "█" else " "} ┃\n" +
				"┗━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┛\n")
	}
}