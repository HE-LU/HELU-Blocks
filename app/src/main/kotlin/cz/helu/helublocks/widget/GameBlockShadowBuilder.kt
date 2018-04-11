package cz.helu.helublocks.widget

import android.graphics.Canvas
import android.graphics.Point
import android.view.View


class GameBlockShadowBuilder(view: View, private val scale: Float) : View.DragShadowBuilder(view) {
	override fun onProvideShadowMetrics(shadowSize: Point, shadowTouchPoint: Point) {
		val v = view
		val width = (v.width * scale).toInt()
		val height = (v.height * scale).toInt()
		shadowSize.set(width, height)
		shadowTouchPoint.set(width / 2, height / 2)
	}


	override fun onDrawShadow(canvas: Canvas) {
		canvas.scale(scale, scale)
		view.draw(canvas)
	}
}