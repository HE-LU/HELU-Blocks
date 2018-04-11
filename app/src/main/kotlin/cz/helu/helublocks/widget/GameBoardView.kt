package cz.helu.helublocks.widget

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import cz.helu.helublocks.HeluBlocksConfig.COLUMN_COUNT
import cz.helu.helublocks.R
import cz.helu.helublocks.extension.extGetDrawable
import cz.helu.helublocks.viewmodel.GameViewModel


class GameBoardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
	private var drawableWidth = 0f
	private var drawableHeight = 0f
	private val paintGameBoardBorder = Paint()
	private val paintGameBoardGrid = Paint()
	private var gameViewModel: GameViewModel? = null


	object GameBoardViewBindingAdapters {
		@JvmStatic
		@BindingAdapter("app:bindViewModel", requireAll = false)
		fun bindViewModel(view: GameBoardView, viewModelListener: GameViewModel?) {
			view.gameViewModel = viewModelListener
		}
	}


	init {
		setupPaints()
	}


	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		drawableWidth = View.resolveSizeAndState(View.MeasureSpec.getSize(drawableWidth.toInt()), widthMeasureSpec, 1).toFloat()
		drawableHeight = View.resolveSizeAndState(View.MeasureSpec.getSize(drawableHeight.toInt()), heightMeasureSpec, 1).toFloat()
		setMeasuredDimension(drawableWidth.toInt(), drawableHeight.toInt())
	}


	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)

		drawBackground(canvas)
		drawBlocks(canvas)
	}


	private fun drawBackground(canvas: Canvas) {
		gameViewModel?.let { gameViewModel ->
			// Tt Clear canvas
			canvas.drawColor(ContextCompat.getColor(context, R.color.game_board_bg))

			// Tt Draw border line
			canvas.drawRect(0.0f, 0.0f, drawableWidth, drawableHeight, paintGameBoardBorder)

			// Tt Draw grid lines
			val step = drawableWidth / COLUMN_COUNT

			for (x in 1 until COLUMN_COUNT) {
				canvas.drawLine(x * step, 0.0f, x * step, drawableHeight, paintGameBoardGrid)
			}

			for (y in 1 until COLUMN_COUNT) {
				canvas.drawLine(0.0f, y * step, drawableWidth, y * step, paintGameBoardGrid)
			}
		}
	}


	private fun drawBlocks(canvas: Canvas) {
		gameViewModel?.let { gameViewModel ->
			val size = COLUMN_COUNT
			val step = drawableWidth / size

			for (x in 0 until size) {
				for (y in 0 until size) {
					if (gameViewModel.board[x][y] != null) {
						val block = gameViewModel.board[x][y]
						block?.let {
							val drawable = block.drawable.extGetDrawable()
							drawable?.setBounds((y * step).toInt(), (x * step).toInt(), ((y + 1) * step).toInt(), ((x + 1) * step).toInt())
							drawable?.draw(canvas)
						}
					}
				}
			}
		}
	}


	private fun setupPaints() {
		// Tt Game Board Border
		paintGameBoardBorder.color = ContextCompat.getColor(context, R.color.game_board_border)
		paintGameBoardBorder.style = Paint.Style.STROKE
		paintGameBoardBorder.strokeWidth = resources.getDimensionPixelSize(R.dimen.game_board_border_thickness).toFloat()
		paintGameBoardBorder.strokeCap = Paint.Cap.SQUARE

		// Tt Game Board Grid
		paintGameBoardGrid.color = ContextCompat.getColor(context, R.color.game_board_grid)
		paintGameBoardGrid.style = Paint.Style.STROKE
		paintGameBoardGrid.strokeWidth = resources.getDimensionPixelSize(R.dimen.game_board_grid_thickness).toFloat()
	}
}