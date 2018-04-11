package cz.helu.helublocks


object HeluBlocksConfig {
	@JvmStatic
	val VERSION_NAME = BuildConfig.VERSION_NAME
	val DEBUG = BuildConfig.DEBUG
	const val LOGS = BuildConfig.LOGS
	const val CRASHLYTICS = BuildConfig.CRASHLYTICS
	const val SUPPORT_EMAIL = "support-blocks@helu.cz"
	const val PREFS_NAME = "HeluBlocksPrefs"

	// TT Game Board configuration
	const val COLUMN_COUNT = 8
}