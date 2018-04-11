package cz.helu.helublocks.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import cz.helu.helublocks.databinding.FragmentGameBinding
import cz.helu.helublocks.extension.viewModelProvider
import cz.helu.helublocks.ui.GameView
import cz.helu.helublocks.utility.ActivityUtility
import cz.helu.helublocks.viewmodel.GameViewModel


class GameFragment : BaseBindingFragment<GameViewModel, FragmentGameBinding>(), GameView {
	override fun inflateBindingLayout(inflater: LayoutInflater): FragmentGameBinding {
		return FragmentGameBinding.inflate(inflater)
	}


	override fun setupViewModel(): GameViewModel {
		val viewModel = viewModelProvider { GameViewModel(getExtras()) }
		lifecycle.addObserver(viewModel)
		return viewModel
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.executePendingBindings()
	}
}