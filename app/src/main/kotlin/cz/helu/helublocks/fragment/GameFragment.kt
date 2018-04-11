package cz.helu.helublocks.fragment

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import cz.helu.helublocks.HeluBlocksConfig.COLUMN_COUNT
import cz.helu.helublocks.R
import cz.helu.helublocks.databinding.FragmentGameBinding
import cz.helu.helublocks.entity.GameBlock
import cz.helu.helublocks.extension.extGetDimensionPixelSize
import cz.helu.helublocks.extension.viewModelProvider
import cz.helu.helublocks.ui.GameView
import cz.helu.helublocks.viewmodel.GameViewModel
import cz.helu.helublocks.widget.GameBlockShadowBuilder
import cz.helu.helublocks.widget.GameBlockView
import extStartDragAndDrop


class GameFragment : BaseBindingFragment<GameViewModel, FragmentGameBinding>(), GameView {
	override fun inflateBindingLayout(inflater: LayoutInflater): FragmentGameBinding {
		return FragmentGameBinding.inflate(inflater)
	}


	override fun setupViewModel(): GameViewModel {
		val viewModel = viewModelProvider { GameViewModel(getExtras()) }
		lifecycle.addObserver(viewModel)
		return viewModel
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.executePendingBindings()

		bindView()
	}


	private fun bindView() {
		// TT Add three blocks!
		addNewBlock()
		addNewBlock()
		addNewBlock()

		// TT Setup Drag listener for GameBoard
		binding.gameBoardView.setOnDragListener { _, event ->
			when (event.action) {
				DragEvent.ACTION_DROP -> {
					// Dropped, reassign View to ViewGroup
					val view = event.localState as GameBlockView
					val lastOwner = view.parent as ViewGroup

					// Tt Add block to game board
					if (handleBlockDrop(view.gameBlock, event.x, event.y)) {
						// Tt Remove block from blocksContainer layout
						lastOwner.removeView(view)

						// Tt Add new block!
						addNewBlock()
					} else {
						view.visibility = View.VISIBLE
					}
				}
			}
			true
		}

		// TT Setup Drag listener for BlockContainer
		val dragListener = View.OnDragListener { _, event ->
			when (event?.action) {
				DragEvent.ACTION_DROP -> {
					val view = event.localState as View
					view.visibility = View.VISIBLE
				}
			}
			true
		}

		binding.blocksContainer.setOnDragListener(dragListener)
		binding.container.setOnDragListener(dragListener)
	}


	@SuppressLint("ClickableViewAccessibility")
	private fun addNewBlock() {
		// TT Create block
		val newBlock = GameBlockView(context)

		// TT Set view layout params
		val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
		layoutParams.setMargins(R.dimen.game_block_margin.extGetDimensionPixelSize(), 0, R.dimen.game_block_margin.extGetDimensionPixelSize(), 0)
		newBlock.layoutParams = layoutParams

		// TT Set touch listener for drag event
		newBlock.setOnTouchListener { view, event ->
			if (event.action == MotionEvent.ACTION_DOWN) {
				val blockPieceSize = view.width / GameBlock.MAX_BLOCK_SIZE
				val gameBoardBlockPieceSize = binding.gameBoardView.width / COLUMN_COUNT
				val scale = gameBoardBlockPieceSize.toFloat() / blockPieceSize.toFloat()
				view.extStartDragAndDrop(ClipData.newPlainText("", ""), GameBlockShadowBuilder(view, scale), view, 0)
				view.visibility = View.INVISIBLE
				true
			} else {
				false
			}
		}

		// Tt Insert block into container
		binding.blocksContainer.addView(newBlock)
	}


	private fun handleBlockDrop(block: GameBlock, x: Float, y: Float): Boolean {
		val gameBoardBlockPieceSize = binding.gameBoardView.width / COLUMN_COUNT

		if (block.blockSize == 2) { // TT 2x2
			// Tt this way we get the center position of drop.
			val posX = ((x - (gameBoardBlockPieceSize / 2)) / gameBoardBlockPieceSize).toInt()
			val posY = ((y - (gameBoardBlockPieceSize / 2)) / gameBoardBlockPieceSize).toInt()

			val wasAddSuccessful = viewModel.setGameBlock(block, posX, posY)

			// Tt Redraw the gameBoardView
			binding.gameBoardView.invalidate()

			return wasAddSuccessful
		} else { // TT 3x3
			// Tt this way we get the center position of drop.
			val posX = (x / gameBoardBlockPieceSize).toInt()
			val posY = (y / gameBoardBlockPieceSize).toInt()

			val wasAddSuccessful = viewModel.setGameBlock(block, posX, posY)

			// Tt Redraw the gameBoardView
			binding.gameBoardView.invalidate()

			return wasAddSuccessful
		}
	}
}