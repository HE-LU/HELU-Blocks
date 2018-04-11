package cz.helu.helublocks.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import cz.helu.helublocks.R


class MainActivity : BaseActivity() {
	companion object {
		fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}
