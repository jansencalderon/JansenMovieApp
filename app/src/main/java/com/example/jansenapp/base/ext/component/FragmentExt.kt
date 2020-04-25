package com.example.jansenapp.base.ext.component

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.requireAppCompatActivity(): AppCompatActivity = requireActivity() as AppCompatActivity

fun Fragment.onBackPressed() = requireActivity().onBackPressed()