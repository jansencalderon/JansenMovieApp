package com.example.jansenapp.di.module

import com.example.jansenapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityComponentModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}