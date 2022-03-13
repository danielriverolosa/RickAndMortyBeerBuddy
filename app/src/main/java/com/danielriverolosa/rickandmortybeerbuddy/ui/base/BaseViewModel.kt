package com.danielriverolosa.rickandmortybeerbuddy.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielriverolosa.domain.dispatcher.DefaultDispatcherProvider
import com.danielriverolosa.domain.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<VS : ViewState, I : Event>(
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {

    private val mutableViewState = MutableStateFlow<VS?>(null)

    val viewState: StateFlow<VS?>
        get() = mutableViewState

    protected fun updateViewState(viewState: VS) {
        viewModelScope.launch(dispatcherProvider.main) {
            mutableViewState.emit(viewState)
        }
    }

    abstract fun onEvent(event: I)

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(dispatcherProvider.main) {
            block()
        }
    }
}