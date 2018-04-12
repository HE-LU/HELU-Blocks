package cz.helu.helublocks.entity

import cz.helu.helublocks.R
import java.util.*


class GameBlock {
	var blockSizeX = MAX_BLOCK_SIZE
	var blockSizeY = MAX_BLOCK_SIZE
	var blockPieces: Array<Array<GameBlockPiece?>> = Array(MAX_BLOCK_SIZE, { arrayOfNulls<GameBlockPiece>(MAX_BLOCK_SIZE) })


	companion object {
		const val MAX_BLOCK_SIZE = 3
		private val random = Random()


		fun createRandomBlock(): GameBlock {
			val blockCount = 12
			var number = random.nextInt() % blockCount
			if (number < 0)
				number *= -1

			return when (number % blockCount) {
				0 -> createDotBlock()
				1 -> createSquareBlock()
				2 -> createTBlock()
				3 -> createLOneBlock()
				4 -> createLTwoBlock()
				5 -> createLThreeBlock()
				6 -> createLFourBlock()
				7 -> createIHorizontalBlock()
				8 -> createIVerticalBlock()
				9 -> createIShortHorizontalBlock()
				10 -> createIShortVerticalBlock()
				11 -> createCrossBlock()
				else -> createSquareBlock()
			}
		}


		private fun createDotBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_black
			block.blockSizeX = 1
			block.blockSizeY = 1
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createSquareBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_red
			block.blockSizeX = 2
			block.blockSizeY = 2
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][1] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createTBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_blue
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][2] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][1] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createLOneBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_yellow
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][2] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createLTwoBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_yellow
			block.blockPieces[2][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][2] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][2] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][2] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createLThreeBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_yellow
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][2] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][2] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][2] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createLFourBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_yellow
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][2] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][0] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createIVerticalBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_cyan
			block.blockSizeX = 3
			block.blockSizeY = 1
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][0] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createIHorizontalBlock(): GameBlock {
			val block = GameBlock()
			val drawable = R.drawable.block_pink
			block.blockSizeX = 1
			block.blockSizeY = 3
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][2] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createIShortVerticalBlock(): GameBlock {
			val block = GameBlock()
			block.blockSizeX = 2
			block.blockSizeY = 1
			val drawable = R.drawable.block_cyan
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createIShortHorizontalBlock(): GameBlock {
			val block = GameBlock()
			block.blockSizeX = 1
			block.blockSizeY = 2
			val drawable = R.drawable.block_pink
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}


		private fun createCrossBlock(): GameBlock {
			val block = GameBlock()
			block.blockSizeX = 3
			block.blockSizeY = 3
			val drawable = R.drawable.block_pink
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][2] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[2][1] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}
	}
}