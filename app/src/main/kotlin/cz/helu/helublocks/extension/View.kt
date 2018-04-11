import android.content.ClipData
import android.os.Build
import android.view.View


fun View.extStartDragAndDrop(data: ClipData, shadowBuilder: View.DragShadowBuilder, myLocalState: Any, flags: Int): Boolean {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
		return this.startDragAndDrop(data, shadowBuilder, myLocalState, flags)
	} else {
		return this.startDrag(data, shadowBuilder, myLocalState, flags)
	}
}