package com.example.jansenapp.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.jansenapp.R
import com.example.jansenapp.base.SimpleListAdapter
import com.example.jansenapp.base.utils.BlurTransformation
import com.example.jansenapp.databinding.ItemTrackBinding
import com.example.jansenapp.domain.model.Track

class HomeListAdapter(
    private val onItemClick: (Track) -> Unit
) : SimpleListAdapter<ItemTrackBinding, Track>(
    R.layout.item_track,
    TrackDiffCallback
) {

    override fun bind(
        holder: ViewHolder<ItemTrackBinding>,
        item: Track,
        position: Int
    ) {
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(item.artworkUrl100)
                .dontAnimate()
                .fitCenter()
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(imageView)

            Glide.with(holder.itemView.context)
                .load(item.artworkUrl100)
                .dontAnimate()
                .transform(BlurTransformation(holder.itemView.context))
                .into(imageViewBlur)

            textViewGenre.text = item.primaryGenreName
            textViewTrackName.text = item.nameDisplay
            textViewGenre.text = item.primaryGenreName
            textViewTrackPrice.text = item.priceDisplay

            holder.itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }

    object TrackDiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(
            oldItem: Track,
            newItem: Track
        ): Boolean = oldItem.collectionId == newItem.collectionId

        override fun areContentsTheSame(
            oldItem: Track,
            newItem: Track
        ): Boolean = oldItem == newItem
    }
}