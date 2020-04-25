package com.example.jansenapp.data.remote.models

import com.example.jansenapp.domain.model.Track
import com.google.gson.annotations.SerializedName

data class TrackRepo(
    @SerializedName("wrapperType") val wrapperType: String,
    @SerializedName("kind") val kind: String? = null,
    @SerializedName("artistId") val artistId: Int,
    @SerializedName("collectionId") val collectionId: Int,
    @SerializedName("artistName") val artistName: String,
    @SerializedName("trackName") val trackName: String? = null,
    @SerializedName("collectionName") val collectionName: String? = null,
    @SerializedName("collectionCensoredName") val collectionCensoredName: String? = null,
    @SerializedName("artistViewUrl") val artistViewUrl: String? = null,
    @SerializedName("collectionViewUrl") val collectionViewUrl: String? = null,
    @SerializedName("artworkUrl60") val artworkUrl60: String,
    @SerializedName("artworkUrl100") val artworkUrl100: String,
    @SerializedName("collectionPrice") val collectionPrice: Double,
    @SerializedName("collectionExplicitness") val collectionExplicitness: String,
    @SerializedName("trackCount") val trackCount: Int,
    @SerializedName("copyright") val copyright: String? = null,
    @SerializedName("country") val country: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("primaryGenreName") val primaryGenreName: String,
    @SerializedName("previewUrl") val previewUrl: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("longDescription") val longDescription: String? = null
) {

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