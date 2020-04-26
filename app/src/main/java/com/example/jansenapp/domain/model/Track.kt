package com.example.jansenapp.domain.model

import com.example.jansenapp.base.ext.component.toDate
import com.example.jansenapp.base.ext.component.toReadableString
import com.example.jansenapp.domain.enums.DateTimeFormat

data class Track(
    val collectionId: Int = 0,
    val wrapperType: String = "",
    val kind: String? = "",
    val artistId: Int = 0,
    val artistName: String = "",
    val trackName: String? = null,
    val collectionName: String? = null,
    val collectionCensoredName: String? = null,
    val artistViewUrl: String? = null,
    val collectionViewUrl: String? = null,
    val artworkUrl60: String = "",
    val artworkUrl100: String = "",
    val collectionPrice: Double = 0.0,
    val collectionExplicitness: String = "",
    val trackCount: Int = 0,
    val copyright: String? = null,
    val country: String = "",
    val currency: String = "",
    val releaseDate: String = "",
    val primaryGenreName: String = "",
    val previewUrl: String = "",
    val description: String? = null,
    val longDescription: String? = null
) {

    val nameDisplay
        get() = collectionName ?: trackName ?: "N/A"

    val priceDisplay
        get() = "$currency $collectionPrice"

    val releaseDateDisplay = releaseDate.toDate(DateTimeFormat.API_DATE)?.toReadableString(
        DateTimeFormat.DATE_LONG
    )
}
