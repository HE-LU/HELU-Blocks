package cz.helu.helublocks.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import cz.helu.helublocks.R
import cz.helu.helublocks.entity.GameBlock
import cz.helu.helublocks.extension.extGetDrawable


class GameBlockView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
	private val gameBlock = GameBlock.createRandomBlock()
	private var drawableWidth = 0f
	private var drawableHeight = 0f
	private val paintBlockBorder = Paint()


	init {
		setupPaints()
	}


	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		drawableWidth = View.resolveSizeAndState(View.MeasureSpec.getSize(drawableWidth.toInt()), widthMeasureSpec, 1).toFloat()
		drawableHeight = View.resolveSizeAndState(View.MeasureSpec.getSize(drawableHeight.toInt()), heightMeasureSpec, 1).toFloat()
		drawableHeight = drawableWidth
		setMeasuredDimension(drawableWidth.toInt(), drawableHeight.toInt())
	}


	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)

		// Tt Clear canvas
		canvas.drawColor(Color.TRANSPARENT)

		drawBlock(canvas)
	}


	private fun drawBlock(canvas: Canvas) {
		val blockSize = GameBlock.MAX_BLOCK_SIZE
		val size = gameBlock.blockSize
		val step = drawableWidth / blockSize
		val offset = (blockSize - size) * step / 2

		for (x in 0 until size) {
			for (y in 0 until size) {
				if (gameBlock.blockPieces[x][y] != null) {
					val block = gameBlock.blockPieces[x][y]
					block?.let {
						val drawable = block.drawable.extGetDrawable()
						drawable?.setBounds((offset + (y * step)).toInt(),
								(offset + (x * step)).toInt(),
								(offset + ((y + 1) * step)).toInt(),
								(offset + ((x + 1) * step)).toInt())
						drawable?.draw(canvas)
					}
				}
			}
		}
	}


	private fun setupPaints() {
		// Tt Game Board Border
		paintBlockBorder.color = ContextCompat.getColor(context, R.color.game_board_border)
		paintBlockBorder.style = Paint.Style.STROKE
		paintBlockBorder.strokeWidth = resources.getDimensionPixelSize(R.dimen.game_block_border_thickness).toFloat()
		paintBlockBorder.strokeCap = Paint.Cap.SQUARE
	}
}