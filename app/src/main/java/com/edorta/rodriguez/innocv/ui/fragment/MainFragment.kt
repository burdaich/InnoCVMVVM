package com.edorta.rodriguez.innocv.ui.fragment

import UserAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.edorta.rodriguez.innocv.R
import com.edorta.rodriguez.innocv.databinding.MainFragmentBinding
import com.edorta.rodriguez.innocv.model.UserModel
import com.edorta.rodriguez.innocv.ui.adapter.UserAdapterListener
import com.edorta.rodriguez.innocv.viewmodel.MainViewModel

class MainFragment : Fragment(R.layout.main_fragment), UserAdapterListener {
    private val TAG = MainFragment::class.java.name
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentBinding
    private lateinit var userAdapter: UserAdapter
    private var apiUserList: List<UserModel>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()

        buildListeners()

        buildAdapter()

        binding.swipeRefreshLayout.isRefreshing = true
        binding.clEmpty.visibility = View.INVISIBLE
        mainViewModel.getUsers()
    }

    private fun buildListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.getUsers()
        }

        binding.edtFilter.addTextChangedListener {
            filterList(it.toString())
        }

    }

    private fun subscribeUi() {
        mainViewModel.userModelResponse.observe(viewLifecycleOwner, {
            binding.swipeRefreshLayout.isRefreshing = false
            Log.d(TAG, it.toString())
            apiUserList = it
            userAdapter.updateData(apiUserList)

        })
    }

    private fun buildAdapter() {
        userAdapter = UserAdapter(this)

        binding.rcvResponses.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }
    }

    override fun onUserAdapterClick(userModel: UserModel) {
        Log.d(TAG, userModel.toString())
    }

    private fun filterList(findString: String) {
        val filteredUsers =
            apiUserList?.let {
                it.filter { user ->
                    user.name?.contains(
                        findString,
                        true
                    ) == true
                }
            }

        userAdapter.updateData(filteredUsers)
    }
}