package cz.helu.helublocks.utility

import android.app.Activity
import android.content.Context
import cz.helu.helublocks.activity.GameActivity


object ActivityUtility {
	@JvmStatic
	fun startGameActivity(activityContext: Activity) {
		val intent = GameActivity.newIntent(activityContext)
		activityContext.startActivity(intent)
	}
}
