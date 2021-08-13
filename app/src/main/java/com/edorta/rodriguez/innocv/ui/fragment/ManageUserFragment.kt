package com.edorta.rodriguez.innocv.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edorta.rodriguez.innocv.R
import com.edorta.rodriguez.innocv.viewmodel.ManageUserViewModel

class ManageUserFragment : Fragment() {

    companion object {
        fun newInstance() = ManageUserFragment()
    }

    private lateinit var viewModel: ManageUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.manage_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ManageUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}