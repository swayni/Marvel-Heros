package sw.swayni.marvelheros.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import sw.swayni.base.mvvm.adapter.BaseRecyclerViewAdapter
import sw.swayni.base.mvvm.adapter.BaseViewHolder
import sw.swayni.marvelheros.data.model.Comic
import sw.swayni.marvelheros.databinding.ItemCharacterComicBinding

class ComicsRecyclerViewAdapter : BaseRecyclerViewAdapter<Comic>() {

    override fun getCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder<Comic> = ComicsViewHolder(ItemCharacterComicBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))


    inner class ComicsViewHolder(private val binding: ItemCharacterComicBinding) : BaseViewHolder<Comic>(binding) {
        override fun bindData(data: Comic) {
            val path = "${data.thumbnail.path.replace("http://","https://")}/standard_amazing.${data.thumbnail.extension}"
            binding.image.load(path){
                crossfade(true)
                size(150, 150)
            }
            binding.nameText.text = data.title
        }
    }
}