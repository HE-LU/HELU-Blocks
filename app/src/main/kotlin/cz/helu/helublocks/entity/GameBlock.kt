package cz.helu.helublocks.entity

import cz.helu.helublocks.R


class GameBlock {
	private val BLOCK_SIZE = 3
	val blockPieces: Array<Array<GameBlockPiece?>> = Array(BLOCK_SIZE, { arrayOfNulls<GameBlockPiece>(BLOCK_SIZE) })

	companion object {
		fun createSquareBlock() : GameBlock
		{
			val block = GameBlock()
			val drawable = R.drawable.block_red
			block.blockPieces[0][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[0][1] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][0] = GameBlockPiece().apply { this.drawable = drawable }
			block.blockPieces[1][1] = GameBlockPiece().apply { this.drawable = drawable }
			return block
		}
	}
}