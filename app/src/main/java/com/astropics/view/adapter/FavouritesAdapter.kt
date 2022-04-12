package com.astropics.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astropics.R
import com.astropics.databinding.AdapterFavItemBinding
import com.astropics.db.entity.PicEntity
import com.astropics.utils.loadImageFromGlide
import javax.inject.Inject

class FavouritesAdapter @Inject constructor() :
    RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterFavItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<PicEntity>() {
        override fun areItemsTheSame(oldItem: PicEntity, newItem: PicEntity): Boolean {

            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PicEntity, newItem: PicEntity): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AdapterFavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picEntity = differ.currentList[position]
        holder.binding.apply {
            imageView.loadImageFromGlide(picEntity.url)
            tvTitle.text = picEntity.title
            tvDesc.text = picEntity.explanation
            tvDate.text = String.format(
                holder.binding.root.context.getString(R.string.posted_on),
                picEntity.picDate
            )
        }

        holder.itemView.setOnClickListener {
            setClickListener?.let {
                it(picEntity)
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var setClickListener: ((picEntity: PicEntity) -> Unit)? = null

    fun onItemClick(listener: (PicEntity) -> Unit) {
        setClickListener = listener
    }
}