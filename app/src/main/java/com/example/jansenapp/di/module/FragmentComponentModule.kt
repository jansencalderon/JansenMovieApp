package com.example.jansenapp.di.module

import com.example.jansenapp.ui.home.HomeFragment
import com.example.jansenapp.ui.home.detail.TrackDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * declare fragments that used dagger
 */
@Module
abstract class FragmentComponentModule {

  @ContributesAndroidInjector
  abstract fun homeFragment(): HomeFragment

  @ContributesAndroidInjector
  abstract fun trackDetailFragment(): TrackDetailFragment


}