package com.example.jansenapp.data.local.models

import com.example.jansenapp.domain.model.Track
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/*
* Could not apply proper nullability of fields
* documentation is limited
*
* */
open class TrackDto() : RealmObject() {
    @PrimaryKey
    var collectionId: Int = 0
    var wrapperType: String = ""
    var kind: String? = ""
    var artistId: Int = 0
    var artistName: String = ""
    var trackName: String? = null
    var collectionName: String? = null
    var collectionCensoredName: String? = null
    var artistViewUrl: String? = null
    var collectionViewUrl: String? = null
    var artworkUrl60: String = ""
    var artworkUrl100: String = ""
    var collectionPrice: Double = 0.0
    var collectionExplicitness: String = ""
    var trackCount: Int = 0
    var copyright: String? = null
    var country: String = ""
    var currency: String = ""
    var releaseDate: String = ""
    var primaryGenreName: String = ""
    var previewUrl: String = ""
    var description: String? = null
    var longDescription: String? = null

    constructor(item: Track) : this() {
        collectionId = item.collectionId
        wrapperType = item.wrapperType
        kind = item.kind
        artistId = item.artistId
        artistName = item.artistName
        trackName = item.trackName
        collectionName = item.collectionName
        collectionCensoredName = item.collectionCensoredName
        artistViewUrl = item.artistViewUrl
        artworkUrl60 = item.artworkUrl60
        artworkUrl100 = item.artworkUrl100
        collectionPrice = item.collectionPrice
        collectionViewUrl = item.collectionViewUrl
        collectionExplicitness = item.collectionExplicitness
        trackCount = item.trackCount
        copyright = item.copyright
        country = item.country
        currency = item.currency
        releaseDate = item.releaseDate
        primaryGenreName = item.primaryGenreName
        previewUrl = item.previewUrl
        description = item.description
        longDescription = item.longDescription
    }

    val toTrackDomain: Track
        get() = Track(
            collectionId = collectionId,
            wrapperType = wrapperType,
            kind = kind,
            artistId = artistId,
            artistName = artistName,
            trackName = trackName,
            collectionName = collectionName,
            collectionCensoredName = collectionCensoredName,
            artistViewUrl = artistViewUrl,
            artworkUrl60 = artworkUrl60,
            artworkUrl100 = artworkUrl100,
            collectionPrice = collectionPrice,
            collectionViewUrl = collectionViewUrl,
            collectionExplicitness = collectionExplicitness,
            trackCount = trackCount,
            copyright = copyright,
            country = country,
            currency = currency,
            releaseDate = releaseDate,
            primaryGenreName = primaryGenreName,
            previewUrl = previewUrl,
            description = description,
            longDescription = longDescription
        )
}
