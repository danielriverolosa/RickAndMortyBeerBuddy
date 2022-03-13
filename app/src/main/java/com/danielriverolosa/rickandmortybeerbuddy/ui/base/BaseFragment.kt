package com.danielriverolosa.rickandmortybeerbuddy.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewBinding, VM : BaseViewModel<VS, *>, VS : ViewState> :
    Fragment(), Renderable<VS> {

    protected abstract val viewModel: VM
    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = viewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.launchWhenStarted(viewLifecycleOwner)
    }

    private fun StateFlow<VS?>.launchWhenStarted(lifecycleOwner: LifecycleOwner) = with(lifecycleOwner) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@launchWhenStarted.collect{
                    it?.let { render(it) }
                }
            }
        }
    }

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    protected fun showError(root: ViewGroup, message: String)  {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
    }
}