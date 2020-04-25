package com.example.jansenapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton


@Module
class ApplicationModule() {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration? {
        val builder: RealmConfiguration.Builder = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
        return builder.build()
    }

    @Provides
    fun provideDefaultRealm(config: RealmConfiguration): Realm {
        return Realm.getInstance(config)
    }

}