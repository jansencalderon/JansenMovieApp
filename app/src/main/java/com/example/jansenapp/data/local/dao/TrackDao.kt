package com.example.jansenapp.data.local.dao

import com.example.jansenapp.data.local.models.TrackDto
import com.example.jansenapp.data.local.utils.findAllObservable
import com.example.jansenapp.data.local.utils.findObservable
import com.example.jansenapp.data.local.utils.saveToRealm
import com.example.jansenapp.domain.model.Track
import io.reactivex.Observable
import io.realm.Realm
import javax.inject.Inject

class TrackDao @Inject constructor() {

    /**
     * Returns the track value of the given [id].
     */
    fun get(id: Int): Observable<Track> = Realm.getDefaultInstance().where(TrackDto::class.java)
        .equalTo(TrackDto::collectionId.name, id)
        .findObservable()
        .onErrorReturn { null }
        .map {
            it.toTrackDomain
        }

    /**
     * Returns all tracks saved
     */
    fun getAll(): Observable<List<Track>> =
        Realm.getDefaultInstance().where(TrackDto::class.java)
            .findAllObservable()
            .map { list ->
                list.map {
                    it.toTrackDomain
                }
            }
    /**
     * Saves [data] to local db.
     */
    fun saveAll(
        data: List<Track>
    ){
        val realm = Realm.getDefaultInstance()
        val tracksDto: List<TrackDto> = data.map { TrackDto(it) }
        realm.beginTransaction()
        realm.delete(TrackDto::class.java)
        realm.saveToRealm(tracksDto)
        realm.commitTransaction()
        realm.close()
    }

}