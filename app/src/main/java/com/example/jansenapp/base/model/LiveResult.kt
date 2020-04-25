package com.example.jansenapp.base.model

sealed class LiveResult<out T> {

  data class Success<T>(val value: T) : LiveResult<T>()

  data class Failed(val error: Throwable) : LiveResult<Nothing>()

  object Loading : LiveResult<Nothing>()

}