package com.example.jansenapp.data.local.utils

import com.example.jansenapp.base.ext.error
import com.example.jansenapp.base.ext.success
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmQuery
import com.example.jansenapp.data.local.utils.RealmHelper.rxTransaction
import timber.log.Timber
import java.lang.IllegalStateException

object RealmHelper {

  fun <T : Any> rxTransaction(transaction: (Realm) -> T): Single<T> = Single.create<T> { emitter ->
    try {
      Realm.getDefaultInstance()
          .use { realm ->
            var result: T? = null
            Timber.d("rxTransaction thread: ${Thread.currentThread().name}")
            realm.executeTransaction {
              result = transaction.invoke(it)
            }
            emitter.success(result)
          }
    } catch (e: Throwable) {
      Timber.e(e)
      emitter.error(e)
    }
  }

}

inline fun <reified T : RealmObject> List<T>.saveToRealm(): Single<List<T>> = rxTransaction {
  it.copyFromRealm(it.copyToRealmOrUpdate(this))
}

inline fun <reified T : RealmObject> T.saveToRealm(): Single<T> = rxTransaction {
  it.copyFromRealm(it.copyToRealmOrUpdate(this))
}

inline fun <reified T : RealmObject> Realm.saveToRealm(items: List<T>): List<T> =
  this.copyFromRealm(this.copyToRealmOrUpdate(items))

inline fun <reified T : RealmObject> Realm.saveToRealm(item: T): T =
  this.copyFromRealm(this.copyToRealmOrUpdate(item))

inline fun <reified T : RealmObject> RealmQuery<T>.findAllObservable(): Observable<List<T>> =
  when {
    realm.isAutoRefresh -> findAllAsync()
        .asFlowable()
        .doOnNext { Timber.d("findAllObservable thread: ${Thread.currentThread().name}") }
        .filter { it.isLoaded && it.isValid }
        .map {
          it.freeze()
              .toList()
        }
        .toObservable()
    else -> Observable.just(findAll().freeze().toList())
        .doOnNext {
          Timber.d("findAllObservable non auto-refresh thread: ${Thread.currentThread().name}")
        }
  }

inline fun <reified T : RealmObject> RealmQuery<T>.findObservable(): Observable<T> = when {
  realm.isAutoRefresh -> findAllAsync()
      .asFlowable()
      .doOnNext { Timber.d("findAllObservable thread: ${Thread.currentThread().name}") }
      .filter { it.isLoaded && it.isValid }
      .map {
        it.freeze().firstOrNull() ?: throw IllegalStateException("No object found")
      }
      .toObservable()
  else -> Observable.just(
      findAll().let {
        it.freeze().firstOrNull() ?: throw IllegalStateException("No object found")
      })
      .doOnNext {
        Timber.d("findAllObservable non auto-refresh thread: ${Thread.currentThread().name}")
      }
}

inline fun <reified P : Any, T : RealmObject> RealmQuery<T>.equalToIfNotNull(
  fieldName: String,
  value: P?
): RealmQuery<T> = this.let {
  value?.let {
    when (P::class) {
      String::class -> this.equalTo(fieldName, value as String)
      Boolean::class -> this.equalTo(fieldName, value as Boolean)
      else -> throw UnsupportedOperationException("Not yet implemented")
    }
  } ?: this
}