package cz.helu.helublocks.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cz.helu.helublocks.R


class GameActivity : BaseActivity() {
	companion object {
		fun newIntent(context: Context): Intent = Intent(context, GameActivity::class.java)
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_game)
	}
}
