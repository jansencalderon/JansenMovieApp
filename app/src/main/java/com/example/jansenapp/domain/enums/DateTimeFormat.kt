package com.example.jansenapp.domain.enums

enum class DateTimeFormat {

  API_DATE {
    override val value: String
      get() = "yyyy-MM-dd'T'HH:mm:ssZ"
  },
  API_DATE_NO_TZ {
    override val value: String
      get() = "dd MMM yyyy'T'hh:mm aa"
  },
  DATE_LONG {
    override val value: String
      get() = "MMMM dd, yyyy"
  },
  DATE_SHORT {
    override val value: String
      get() = "dd MMM yyyy"
  },
  DATE_TIME {
    override val value: String
      get() = "HH:mm '|' dd MMM yyyy"
  },
  COUNTDOWN_FORMAT {
    override val value: String
      get() = "%02d days %02d hours %02d minutes %02d seconds"
  },
  DATE_ONLY {
    override val value: String
      get() = "yyyy-MM-dd"
  },
  DATE_MONTH {
    override val value: String
      get() = "MMM dd"
  },
  TIME_ONLY {
    override val value: String
      get() = "hh:mm aa"
  };

  abstract val value: String

}