package sw.swayni.marvelheros.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import sw.swayni.base.mvvm.adapter.BaseRecyclerViewAdapter
import sw.swayni.base.mvvm.adapter.BaseViewHolder
import sw.swayni.marvelheros.data.model.Character
import sw.swayni.marvelheros.databinding.ItemCharacterComicBinding

class CharacterListAdapter(listener:(Character) -> Unit): BaseRecyclerViewAdapter<Character>() {

    init {
        itemClickListener = listener
    }

    override fun getCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Character> =
        CharacterViewHolder(ItemCharacterComicBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))


    inner class CharacterViewHolder(private val binding: ItemCharacterComicBinding): BaseViewHolder<Character>(binding){
        override fun bindData(data: Character) {
            val imagePath = "${data.thumbnail.path.replace("http://","https://")}/standard_amazing.${data.thumbnail.extension}"
            binding.image.load(imagePath){
                crossfade(true)
                this.size(150, 150)
            }

            binding.nameText.text = data.name
        }
    }


    fun getLastItem(): Int = list.lastIndex
}