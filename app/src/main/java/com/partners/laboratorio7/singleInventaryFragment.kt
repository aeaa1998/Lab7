package com.partners.laboratorio7

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class singleInventaryFragment : Fragment() {

    companion object {
        fun newInstance() = singleInventaryFragment()
    }

    private lateinit var viewModel: SingleInventaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.single_inventary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SingleInventaryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
