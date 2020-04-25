package com.example.jansenapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jansenapp.base.model.LiveResult
import com.example.jansenapp.domain.enums.Kind
import com.example.jansenapp.domain.model.LastVisitedDate
import com.example.jansenapp.domain.model.Track
import com.example.jansenapp.domain.repository.LastVisitedDateRepository
import com.example.jansenapp.domain.repository.TrackRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val tracksRepository: TrackRepository,
    private val lastVisitedDateRepository: LastVisitedDateRepository
) : ViewModel() {

    private val _trackList: MutableLiveData<List<Track>> = MutableLiveData()
    private val _tvShowList: MutableLiveData<List<Track>> = MutableLiveData()
    private val _audioBookList: MutableLiveData<List<Track>> = MutableLiveData()
    private val _filmList: MutableLiveData<List<Track>> = MutableLiveData()

    private val _lastVisitedDate: MutableLiveData<LastVisitedDate> = MutableLiveData()

    private val _showLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun getTrackList() = _trackList
    fun getTvShowList() = _tvShowList
    fun getAudioBookList() = _audioBookList
    fun getFilmList() = _filmList

    fun isLoading() = _showLoading
    fun getError() = _error

    fun getLastVisitedDate() = _lastVisitedDate

    /*
    * Get Tracks from Repo
    *
    * Used Kotlin Collections filter to add some flavor in displaying it by [kind]
    * with exception to audiobook as it is just used on wrapperType
    *
    * TODO: Better Implemention to filter on Repo, Local and Remote Source for better performance
    *
    * */
    fun loadTracks() {
        tracksRepository.getAllTracks()
            .doOnError { _error.postValue(it) }
            .doOnSubscribe { _showLoading.postValue(true) }
            .subscribe {
                _showLoading.postValue(false)
                _trackList.postValue(it.filter { track -> track.kind == Kind.TRACK.value  })
                _audioBookList.postValue(it.filter { track -> track.wrapperType ==  Kind.AUDIO_BOOK.value  })
                _tvShowList.postValue(it.filter { track -> track.kind ==  Kind.TV_SHOW.value  })
                _filmList.postValue(it.filter { track -> track.kind ==  Kind.FILM.value   })
            }
            .addTo(disposables)
    }

    fun loadLastVisitedDate(){
        _lastVisitedDate.postValue(lastVisitedDateRepository.getLastVisitedDate())
    }

    override fun onCleared() {
        super.onCleared()
        lastVisitedDateRepository.saveLastVisitedDate()
        disposables.clear()
    }


}
