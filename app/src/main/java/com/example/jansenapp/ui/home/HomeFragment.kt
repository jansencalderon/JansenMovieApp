package com.example.jansenapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jansenapp.R
import com.example.jansenapp.base.BaseFragment
import com.example.jansenapp.base.ext.component.makeVisible
import com.example.jansenapp.base.ext.component.makeVisibleOrGone
import com.example.jansenapp.base.ext.component.toRelativeDateTime
import com.example.jansenapp.base.model.LiveResult
import com.example.jansenapp.data.remote.models.TrackRepo
import com.example.jansenapp.databinding.FragmentHomeBinding
import com.example.jansenapp.base.ext.withBinding
import com.example.jansenapp.base.ext.withViewModel
import com.example.jansenapp.domain.model.Track
import io.reactivex.Observable

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val homeListAdapterTracks =
        HomeListAdapter(::onItemClick)

    private val homeListAdapterFilms =
        HomeListAdapter(::onItemClick)

    private val homeListAdapterAudioBooks =
        HomeListAdapter(::onItemClick)

    private val homeListAdapterTvShows =
        HomeListAdapter(::onItemClick)

    companion object {
        fun newInstance() =
            HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = withBinding(inflater, R.layout.fragment_home, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = withViewModel(this, viewModelFactory) {
            loadTracks()
            loadLastVisitedDate()
        }

        viewModel.isLoading().observe(viewLifecycleOwner, Observer {
            binding.swipeRefreshLayout.isRefreshing = it
        })

        viewModel.getTrackList().observe(viewLifecycleOwner, Observer {
            homeListAdapterTracks.submitList(it)
        })

        viewModel.getFilmList().observe(viewLifecycleOwner, Observer {
            homeListAdapterFilms.submitList(it)
        })

        viewModel.getTvShowList().observe(viewLifecycleOwner, Observer {
            homeListAdapterTvShows.submitList(it)
        })

        viewModel.getAudioBookList().observe(viewLifecycleOwner, Observer {
            homeListAdapterAudioBooks.submitList(it)
        })

        viewModel.getLastVisitedDate().observe(viewLifecycleOwner, Observer { lastVisitedDate ->
            lastVisitedDate?.let {
                binding.textViewLastVisited.apply {
                    makeVisible()
                    text = "Last Visited: ${lastVisitedDate.lastVisited.toRelativeDateTime(
                        requireContext()
                    )}"
                }
            }
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer {
            showToast(
                it.localizedMessage ?: "Something went wrong, Please check your internet connection"
            )
        })

        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.loadTracks()
            }
            recyclerViewFilms.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = homeListAdapterFilms
            }
            recyclerViewTracks.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = homeListAdapterTracks
            }
            recyclerViewTvShows.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = homeListAdapterTvShows
            }
            recyclerViewAudiobooks.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = homeListAdapterAudioBooks
            }
        }

    }

    private fun onItemClick(track: Track) {
        when (findNavController().currentDestination?.id) {
            R.id.homeFragment -> findNavController().navigate(
                HomeFragmentDirections.goToTrackDetailFragment(
                    track.collectionId
                )
            )
        }
    }

}
