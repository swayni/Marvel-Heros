package sw.swayni.marvelheros.ui.detail.presentation

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import sw.swayni.base.mvvm.core.observe
import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.view.BindingFragment
import sw.swayni.marvelheros.R
import sw.swayni.marvelheros.data.model.CharacterModel
import sw.swayni.marvelheros.databinding.FragmentDetailBinding
import sw.swayni.marvelheros.ui.detail.ComicsRecyclerViewAdapter
import sw.swayni.marvelheros.ui.detail.viewmodel.DetailViewModel

@AndroidEntryPoint
class DetailFragment : BindingFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail, DetailViewModel::class.java)  {

    private val navArgsDetailFragment by navArgs<DetailFragmentArgs>()
    private val character by lazy { navArgsDetailFragment.character }

    private val adapter by lazy { ComicsRecyclerViewAdapter() }

    private lateinit var characterModel : CharacterModel

    override fun initUI() {
        binding.recyclerview.adapter = adapter

        character?.let {
            val imagePath = "${it.thumbnail.path.replace("http://","https://")}/standard_amazing.${it.thumbnail.extension}"
            binding.image.load(imagePath){
                crossfade(true)
                size(150,150)
            }
            binding.nameText.text = it.name
            if (it.description != ""){
                binding.descriptionTitle.visibility = VISIBLE
                binding.descriptionText.visibility = VISIBLE
                binding.descriptionText.text = it.description
            }
            else{
                binding.descriptionTitle.visibility = GONE
                binding.descriptionText.visibility = GONE
            }

            characterModel = CharacterModel(it.id, it.name, it.description, imagePath)
            viewModel.getFavorite(it.id)
            viewModel.getComics(it.id)
        }

        binding.favoriteButton.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected){
                viewModel.addFavorite(characterModel)
            }
            else {
                viewModel.deleteFavorite(characterModel)
            }
        }
    }

    override fun observeData() {}

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
                    it.comicDataContainer?.data?.let { data->
                        if(data.results.isNotEmpty()) {
                            binding.comicText.visibility = VISIBLE
                            binding.recyclerview.visibility = VISIBLE
                            adapter.addList(data.results)
                        }
                    }
                    it.isFavorite?.let { isFavorite->
                        binding.favoriteButton.isSelected = isFavorite
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