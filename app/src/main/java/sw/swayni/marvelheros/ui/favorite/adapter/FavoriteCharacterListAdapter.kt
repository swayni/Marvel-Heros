package sw.swayni.marvelheros.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import sw.swayni.base.mvvm.adapter.BaseRecyclerViewAdapter
import sw.swayni.base.mvvm.adapter.BaseViewHolder
import sw.swayni.marvelheros.data.model.CharacterModel
import sw.swayni.marvelheros.databinding.ItemCharacterComicBinding

class FavoriteCharacterListAdapter : BaseRecyclerViewAdapter<CharacterModel>() {

    override fun getCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CharacterModel> =
        FavoriteCharacterListViewHolder(ItemCharacterComicBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false))

    inner class FavoriteCharacterListViewHolder(private val binding: ItemCharacterComicBinding):BaseViewHolder<CharacterModel>(binding){
        override fun bindData(data: CharacterModel) {
            binding.image.load(data.imagePath){
                crossfade(true)
                size(150, 150)
            }
            binding.nameText.text = data.name
        }
    }
}