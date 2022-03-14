package com.danielriverolosa.rickandmortybeerbuddy.ui.utils

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

fun RecyclerView.endless(visibleThreshold: Int = 10, loadMore: () -> Unit) {
    this.addOnScrollListener(EndlessScroll(this, visibleThreshold, loadMore))
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}