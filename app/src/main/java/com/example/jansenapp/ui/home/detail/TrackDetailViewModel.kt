package com.example.jansenapp.ui.home.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jansenapp.base.model.LiveResult
import com.example.jansenapp.domain.model.Track
import com.example.jansenapp.domain.repository.TrackRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TrackDetailViewModel @Inject constructor(private val trackRepository: TrackRepository) : ViewModel() {


    private val _track: MutableLiveData<LiveResult<Track>> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun getTrack() = _track

    fun loadTrack(id: Int) {
        trackRepository.getTrack(id)
            .doOnError { _track.postValue(LiveResult.Failed(it)) }
            .subscribe {
                _track.postValue(LiveResult.Success(it))
            }
            .addTo(disposables)

    }
}
