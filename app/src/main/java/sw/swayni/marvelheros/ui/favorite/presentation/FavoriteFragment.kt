package sw.swayni.marvelheros.ui.favorite.presentation

import androidx.appcompat.app.AlertDialog
import dagger.hilt.android.AndroidEntryPoint
import sw.swayni.base.mvvm.core.observe
import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.view.BindingFragment
import sw.swayni.marvelheros.R
import sw.swayni.marvelheros.databinding.FragmentFavoriteBinding
import sw.swayni.marvelheros.ui.favorite.adapter.FavoriteCharacterListAdapter
import sw.swayni.marvelheros.ui.favorite.viewmodel.FavoriteViewModel

@AndroidEntryPoint
class FavoriteFragment : BindingFragment<FragmentFavoriteBinding, FavoriteViewModel>(R.layout.fragment_favorite, FavoriteViewModel::class.java)   {

    private val adapter by lazy { FavoriteCharacterListAdapter() }

    override fun initUI() {
        binding.recyclerview.adapter = adapter

        viewModel.getFavoriteList()
    }

    override fun createdObserve() {
        observe(viewModel.uiStateFlow){
            showLoading(it.uiState)
            when(it.uiState){
                UiState.ERROR -> {
                    AlertDialog.Builder(requireContext()).setMessage(it.errorMessage).setTitle("Error").setOnCancelListener { dialog->
                        dialog.dismiss()
                    }
                }
                UiState.SUCCESS -> {
                    it.characterList?.let { characterModels ->
                        adapter.updateList(characterModels)
                    }
                }
                UiState.FAILURE -> {
                    AlertDialog.Builder(requireContext()).setMessage(it.errorMessage).setTitle("Error").setOnCancelListener { dialog->
                        dialog.dismiss()
                    }
                }
                else->{}
            }
        }
    }
}