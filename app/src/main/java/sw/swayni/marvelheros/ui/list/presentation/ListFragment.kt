package sw.swayni.marvelheros.ui.list.presentation

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import sw.swayni.base.mvvm.core.observe
import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.view.BindingFragment
import sw.swayni.marvelheros.R
import sw.swayni.marvelheros.data.model.Character
import sw.swayni.marvelheros.databinding.FragmentListBinding
import sw.swayni.marvelheros.ui.list.adapter.CharacterListAdapter
import sw.swayni.marvelheros.ui.list.viewmodel.ListViewModel

@AndroidEntryPoint
class ListFragment : BindingFragment<FragmentListBinding, ListViewModel>(R.layout.fragment_list, ListViewModel::class.java)  {

    private val adapter by lazy { CharacterListAdapter(::itemClickListener) }

    override fun initUI() {
        binding.recyclerview.adapter = adapter

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
                    if (lastItem == adapter.getLastItem()){
                        viewModel.loadMoreCharacters()
                    }
                }
            }
        })

        binding.favoriteButton.setOnClickListener {
            viewModel.navigate(ListFragmentDirections.actionListFragmentToFavoriteFragment())
        }
    }

    override fun observeData() {}

    override fun createdObserve() {
        observe(viewModel.uiStateFlow){
            when(it.uiState){
                UiState.IDLE ->{
                    binding.progressBar.visibility = GONE
                }
                UiState.LOADING -> {
                    binding.progressBar.visibility = VISIBLE
                }
                UiState.ERROR -> {
                    binding.progressBar.visibility = GONE
                    AlertDialog.Builder(requireContext()).setMessage(it.errorMessage).setTitle("Error").setOnCancelListener { dialog->
                        dialog.dismiss()
                    }
                }
                UiState.SUCCESS -> {
                    it.characterList?.let { charactersList->
                        if (it.offset == 0){
                            adapter.updateList(charactersList)
                        }else{
                            adapter.addList(charactersList)
                        }
                    }
                    binding.progressBar.visibility = GONE
                }
                UiState.FAILURE -> {
                    binding.progressBar.visibility = GONE
                }
            }
        }
    }

    private fun itemClickListener(character: Character){
        viewModel.navigate(ListFragmentDirections.actionListFragmentToDetailFragment(character))
    }
}