package com.example.jansenapp.base

import android.widget.Toast
import com.example.jansenapp.di.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseFragment: DaggerFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val disposeBag: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.dispose()
    }

    fun showToast(msg: String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}