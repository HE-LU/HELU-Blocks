package cz.helu.helublocks.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import com.crashlytics.android.Crashlytics
import cz.helu.helublocks.fragment.BaseBindingFragment
import cz.helu.helublocks.utility.CrashlyticsUtility
import org.alfonz.arch.AlfonzActivity
import org.alfonz.utility.Logcat


@SuppressLint("Registered")
open class BaseActivity : AlfonzActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		Logcat.i("[Activity] ${this.javaClass.simpleName}")
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

		super.onCreate(savedInstanceState)
		CrashlyticsUtility.setCurrentActivityKey(this::class.java.simpleName)

		// TT Make whole app Fullscreen and without ActionBar.
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
		supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
	}


	override fun onResume() {
		CrashlyticsUtility.setCurrentActivityKey(this::class.java.simpleName)
		super.onResume()
	}


	override fun finish() {
		super.finish()
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
	}


	override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
		android.R.id.home -> {
			finish()
			true
		}
		else -> super.onOptionsItemSelected(item)
	}


	override fun onBackPressed() {
		val fragmentList = supportFragmentManager.fragments

		var handled = false
		if (fragmentList != null) {
			for (f in fragmentList) {
				if (f is BaseBindingFragment<*, *>) {
					handled = f.onBackPressed()

					if (handled) {
						break
					}
				}
			}
		}

		if (!handled) {
			super.onBackPressed()
		}
	}


	override fun onSaveInstanceState(outState: Bundle?) {
		try {
			super.onSaveInstanceState(outState)
		} catch (e: Exception) {
			Crashlytics.log(1, "ACTIVITY_SAVE_INSTANCE_STATE", "Activity SaveInstanceState crash: ${this.javaClass}\n$e")
		}
	}
}