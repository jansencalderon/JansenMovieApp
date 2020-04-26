package com.example.jansenapp.domain.repository

import com.example.jansenapp.data.local.dao.TrackDao
import com.example.jansenapp.data.remote.service.SongsApiService
import com.example.jansenapp.domain.model.Track
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class TrackRepository @Inject constructor(
    private val api: SongsApiService, private val trackDao: TrackDao
) {

    fun getTrack(id: Int): Observable<Track> = trackDao.get(id)

    fun getAllTracks(): Observable<List<Track>> = Observable
        .mergeArrayDelayError(
            getTracksDatabase(),
            getTracksRemote()
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private fun getTracksRemote(): Observable<List<Track>> =
        api.getSongs()
            .toObservable()
            .distinctUntilChanged()
            .map { response ->
                Timber.d("Tracks from Remote: ${response.results}")
                saveToDatabase(response.results.map { it.toTrackDomain })
                response.results.map {
                    it.toTrackDomain
                }
            }
            .onErrorReturn { emptyList() }

    private fun getTracksDatabase(): Observable<List<Track>> =
        trackDao.getAll()

    private fun saveToDatabase(tracks: List<Track>) {
        Timber.d("Tracks to DB: $tracks")
        trackDao.saveAll(tracks)
    }

}