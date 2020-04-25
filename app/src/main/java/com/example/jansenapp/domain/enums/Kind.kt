package com.example.jansenapp.domain.enums

enum class Kind {
    AUDIO_BOOK {
        override val value: String
            get() = "audiobook"
    },
    TV_SHOW {
        override val value: String
            get() = "tv-episode"
    },
    FILM {
        override val value: String
            get() = "feature-movie"
    },
    TRACK {
        override val value: String
            get() = "song"
    };

    abstract val value: String
}