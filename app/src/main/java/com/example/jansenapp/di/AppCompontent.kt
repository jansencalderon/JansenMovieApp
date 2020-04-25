package com.example.jansenapp.di

import com.example.jansenapp.JansenApplication
import com.example.jansenapp.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        FragmentComponentModule::class,
        ActivityComponentModule::class]
)
interface ApplicationComponent : AndroidInjector<JansenApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<JansenApplication>

}