package cz.helu.helublocks.utility

import com.crashlytics.android.Crashlytics
import cz.helu.helublocks.HeluBlocksConfig


object CrashlyticsUtility {
//	fun setUserInformation(user: UserEntity?) {
//		if (!HeluBlocksConfig.CRASHLYTICS)
//			return
//
//		Crashlytics.setUserEmail(user?.email)
//		Crashlytics.setUserName(user?.firstName + " " + user?.lastName)
//		Crashlytics.setUserIdentifier(user?.id.toString())
//		Crashlytics.setBool("SUBSCRIBED", UserManager.isUserSubscribed())
//		Crashlytics.setBool("FB_LINKED", user?.isFbLinked == 1)
//		Crashlytics.setString("FB_ID", user?.fbId ?: "null")
//	}


	fun setCurrentActivityKey(name: String) {
		if (!HeluBlocksConfig.CRASHLYTICS)
			return

		Crashlytics.setString("ACTIVITY", name)
	}


	fun setCurrentFragmentKey(name: String) {
		if (!HeluBlocksConfig.CRASHLYTICS)
			return

		Crashlytics.setString("FRAGMENT", name)
	}


	fun setCurrentViewModelKey(name: String) {
		if (!HeluBlocksConfig.CRASHLYTICS)
			return

		Crashlytics.setString("VIEW_MODEL", name)
	}


	fun addLogMessage(message: String) {
		if (HeluBlocksConfig.CRASHLYTICS) {
			Crashlytics.log(message)
		}
	}


	fun trackException(throwable: Throwable, cause: String, message: String? = null) {
		if (!HeluBlocksConfig.CRASHLYTICS)
			return

		Crashlytics.setString("cause", cause)
		if (message != null)
			Crashlytics.setString("message", message)

		Crashlytics.logException(throwable)
	}


	fun trackException(source: String, cause: String? = null, message: String? = null) {
		if (!HeluBlocksConfig.CRASHLYTICS)
			return

		if (cause != null)
			Crashlytics.setString("cause", cause)
		if (message != null)
			Crashlytics.setString("message", message)

		Crashlytics.logException(Exception(source))
	}
}



