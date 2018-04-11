package cz.helu.helublocks.extension

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import cz.helu.helublocks.HeluBlocksApplication


fun Int.extGetString(): String = HeluBlocksApplication.getContext().getString(this)
fun Int.extGetBoolean(): Boolean = HeluBlocksApplication.getContext().resources.getBoolean(this)
fun Int.extGetString(vararg arguments: Any): String = HeluBlocksApplication.getContext().getString(this, *arguments)
fun Int.extGetPlural(count: Int): String = HeluBlocksApplication.getContext().resources.getQuantityString(this, count, count)
fun Int.extGetDimensionPixelSize(): Int = HeluBlocksApplication.getContext().resources.getDimensionPixelSize(this)
fun Int.extGetDrawable(): Drawable? = ContextCompat.getDrawable(HeluBlocksApplication.getContext(), this)
fun Int.extGetColor(): Int = ContextCompat.getColor(HeluBlocksApplication.getContext(), this)
fun Int.extGetInt(): Int = HeluBlocksApplication.getContext().resources.getInteger(this)

