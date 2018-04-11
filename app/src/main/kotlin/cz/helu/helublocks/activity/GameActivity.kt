package cz.helu.helublocks.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import cz.helu.helublocks.R
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : BaseActivity() {
	companion object {
		fun newIntent(context: Context): Intent = Intent(context, GameActivity::class.java)
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_game)
	}
}
