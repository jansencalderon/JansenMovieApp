package com.example.jansenapp.di.module

import androidx.lifecycle.ViewModel
import com.example.jansenapp.di.mapkey.ViewModelKey
import com.example.jansenapp.ui.home.HomeViewModel
import com.example.jansenapp.ui.home.detail.TrackDetailFragment
import com.example.jansenapp.ui.home.detail.TrackDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrackDetailViewModel::class)
    abstract fun trackDetailViewModel(track: TrackDetailViewModel): ViewModel
}