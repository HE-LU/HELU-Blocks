package cz.helu.helublocks.entity

import cz.helu.helublocks.R
import java.util.*


class GameBlock {
	var blockSize = MAX_BLOCK_SIZE
	var blockPieces: Array<Array<GameBlockPiece?>> = Array(MAX_BLOCK_SIZE, { arrayOfNulls<GameBlockPiece>(MAX_BLOCK_SIZE) })

	companion object {
		const val MAX_BLOCK_SIZE = 3
		private val random = Random()

		fun createRandomBlock(): GameBlock {
			var number = random.nextInt() % 5
			if (number < 0)
				number *= -1

			return when (number % 4) {
				0 -> createSquareBlock()
				1 -> createTBlock()
				2 -> createLBlock()
				3 -> createIHorizontalBlock()
				4 -> createIVerticalBlock()
				else -> createSquareBlock()
			}
		}


		fun createSquareBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_red
			block.blockSize = 2
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][1] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		fun createTBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_red
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][2] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][1] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		fun createLBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_red
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][2] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		fun createIVerticalBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_red
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][1] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		fun createIHorizontalBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_red
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][2] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}
	}
}