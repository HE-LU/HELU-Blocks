package cz.helu.helublocks

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.akaita.java.rxjava2debug.RxJava2Debug
import org.alfonz.utility.Logcat


class HeluBlocksApplication : Application() {
	companion object {
		private lateinit var instance: HeluBlocksApplication


		@JvmStatic
		fun getContext(): Context = instance
	}


	init {
		instance = this
	}


	override fun onCreate() {
		super.onCreate()

		// TT Setup Logging
		if (HeluBlocksConfig.LOGS)
			Logcat.init(BuildConfig.LOGS, "HELU-Blocks")

		// TT Enable strict mode for debug
		if (HeluBlocksConfig.DEBUG)
			setupStrictMode()

		// TT Enable RxJava assembly stack collection, to make RxJava crash reports clear and unique
		// Tt Make sure this is called AFTER setting up any Crash reporting mechanism as Crashlytics
		RxJava2Debug.enableRxJava2AssemblyTracking(arrayOf("cz.helu.helublocks"))
	}


	private fun setupStrictMode() {
		StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
				.detectDiskReads()
				.detectDiskWrites()
				.detectNetwork()
				.penaltyLog()
				.build())

		StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects()
				.detectLeakedClosableObjects()
				.penaltyLog()
				.build())
	}
}
