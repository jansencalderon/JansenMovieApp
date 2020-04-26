package com.example.jansenapp.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.jansenapp.R
import com.example.jansenapp.base.BaseFragment
import com.example.jansenapp.base.ext.component.makeVisibleOrGone
import com.example.jansenapp.base.ext.withBinding
import com.example.jansenapp.base.ext.withViewModel
import com.example.jansenapp.base.model.LiveResult
import com.example.jansenapp.base.utils.BlurTransformation
import com.example.jansenapp.databinding.FragmentTrackDetailBinding
import com.example.jansenapp.domain.model.Track

class TrackDetailFragment : BaseFragment() {

    companion object {
        private const val ARG_TRACK_ID = "trackId"

        fun newInstance(trackId: Int?) = TrackDetailFragment().apply {
            arguments = bundleOf(ARG_TRACK_ID to trackId)
        }

    }

    private var trackId: Int = -1
    private lateinit var viewModel: TrackDetailViewModel
    private lateinit var binding: FragmentTrackDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trackId = requireArguments().getInt(ARG_TRACK_ID)
        if (trackId == -1) {
            throw IllegalStateException("$ARG_TRACK_ID is required")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = withBinding(inflater, R.layout.fragment_track_detail, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = withViewModel(this, viewModelFactory) {
            loadTrack(trackId)
        }



        viewModel.getTrack().observe(viewLifecycleOwner, Observer {
            when (it) {
                is LiveResult.Success -> displayTrackDate(it.value)
            }
        })
    }


    private fun displayTrackDate(track: Track) {
        binding.apply {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            Glide.with(requireContext())
                .load(track.artworkUrl100)
                .dontAnimate()
                .fitCenter()
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(imageViewThumbnail)

            Glide.with(requireContext())
                .load(track.artworkUrl100)
                .dontAnimate()
                .transform(BlurTransformation(requireContext()))
                .into(imageViewBlur)

            collapsingToolbar.title = track.nameDisplay
            textViewDisplayName.text = track.nameDisplay
            textViewArtist.text = track.artistName
            textViewGenre.text = track.primaryGenreName
            textViewTrackPrice.text = track.priceDisplay
            textViewReleaseDate.text = track.releaseDateDisplay


            textViewCopyright.text = track.copyright
            textViewCopyright.makeVisibleOrGone(textViewCopyright.text.isNotEmpty())

            textViewSynopsis.text = track.longDescription ?: track.description
            textViewSynopsisHeader.makeVisibleOrGone(textViewSynopsis.text.isNotEmpty())
            textViewSynopsis.makeVisibleOrGone(textViewSynopsis.text.isNotEmpty())
        }
    }

}
