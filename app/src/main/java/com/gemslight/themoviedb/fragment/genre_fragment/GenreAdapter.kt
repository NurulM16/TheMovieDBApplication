package com.gemslight.themoviedb.fragment.genre_fragment

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gemslight.common.entity.genre.Genre
import com.gemslight.themoviedb.databinding.LayoutGenreItemBinding

class GenreAdapter(
    val isSelected: (Long) -> Boolean
) : RecyclerView.Adapter<GenreViewHolder>() {


    val listDiffer = AsyncListDiffer(this, differ)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(
            LayoutGenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), listDiffer
        )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        listDiffer.currentList[position]?.apply {
            holder.bind(this, isSelected(this.id.toLong()))
        }

    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    fun submitData(dataList : List<Genre>){
        listDiffer.submitList(dataList)
    }


    companion object {
        val differ = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onViewAttachedToWindow(holder: GenreViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind(isSelected(holder.itemId))
    }

    inner class GenreItemKeyProvider : ItemKeyProvider<Long>(SCOPE_CACHED) {
        override fun getKey(position: Int): Long? = listDiffer.currentList[position].id.toLong()

        override fun getPosition(key: Long): Int = listDiffer.currentList.indexOfFirst {
            it.id.toLong() == key
        }
    }

    fun getItemKeyProvider() = GenreItemKeyProvider()

    override fun getItemId(position: Int): Long = listDiffer.currentList[position].id.toLong()
}

class GenreItemDetailsLookUp(val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? =
        recyclerView.findChildViewUnder(e.x, e.y)?.let {
            (recyclerView.getChildViewHolder(it) as GenreViewHolder).getItemDetail()
        }

}


class GenreViewHolder(val binding: LayoutGenreItemBinding, val list: AsyncListDiffer<Genre>) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: Genre, selection: Boolean) {
        binding.data = genre
        binding.isSelected = selection
    }

    fun bind(selection: Boolean) {
        binding.isSelected = selection
    }

    fun getItemDetail() = object : ItemDetailsLookup.ItemDetails<Long>() {
        override fun getPosition(): Int = absoluteAdapterPosition

        override fun getSelectionKey(): Long? = list.currentList[adapterPosition].id.toLong()
    }
}