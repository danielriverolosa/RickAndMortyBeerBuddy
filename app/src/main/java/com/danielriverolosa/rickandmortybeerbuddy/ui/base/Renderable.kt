package com.danielriverolosa.rickandmortybeerbuddy.ui.base

interface Renderable<VS : ViewState> {
    fun render(viewState: VS)
}