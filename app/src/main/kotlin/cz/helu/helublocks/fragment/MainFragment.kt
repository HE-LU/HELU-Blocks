package cz.helu.helublocks.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import cz.helu.helublocks.databinding.FragmentMainBinding
import cz.helu.helublocks.extension.viewModelProvider
import cz.helu.helublocks.ui.MainView
import cz.helu.helublocks.utility.ActivityUtility
import cz.helu.helublocks.viewmodel.MainViewModel


class MainFragment : BaseBindingFragment<MainViewModel, FragmentMainBinding>(), MainView {
	override fun inflateBindingLayout(inflater: LayoutInflater): FragmentMainBinding {
		return FragmentMainBinding.inflate(inflater)
	}


	override fun setupViewModel(): MainViewModel {
		val viewModel = viewModelProvider { MainViewModel(getExtras()) }
		lifecycle.addObserver(viewModel)
		return viewModel
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.executePendingBindings()
	}


	override fun startGameClick() {
		ActivityUtility.startGameActivity(activity as Activity)
	}
}