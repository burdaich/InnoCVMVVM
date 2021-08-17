package com.edorta.rodriguez.innocv.ui.fragment

import DatePickerFragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.edorta.rodriguez.innocv.R
import com.edorta.rodriguez.innocv.databinding.ManageUserFragmentBinding
import com.edorta.rodriguez.innocv.model.UserModel
import com.edorta.rodriguez.innocv.viewmodel.ManageUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class ManageUserFragment : Fragment(R.layout.manage_user_fragment) {
    private var localDate: LocalDate? = null
    private val TAG = ManageUserFragment::class.java.name
    private lateinit var binding: ManageUserFragmentBinding
    private var userModel: UserModel? = null
    private val args: ManageUserFragmentArgs by navArgs()
    private var reformattedStr: String? = null
    private val manageUserViewModel: ManageUserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ManageUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()

        buildListeners()
        userModel = args.userModel

        setUserModelData()

        manageButtonVisibility()
    }

    private fun manageButtonVisibility() {
        userModel?.let {
            binding.updateB.visibility = View.VISIBLE
            binding.saveB.visibility = View.GONE
        }
    }

    private fun subscribeUi() {
        manageUserViewModel.updatedUserResponse.observe(viewLifecycleOwner, { updateUser ->
            updateUser?.let {
                findNavController().popBackStack()
            }
        })


        manageUserViewModel.saveUserResponse.observe(viewLifecycleOwner, { saveUser ->
            saveUser?.let {
                findNavController().popBackStack()
            }
        })
    }

    private fun setUserModelData() {
        userModel?.let {
            binding.nameET.setText(it.name)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            localDate = LocalDate.parse(it.birthdate, formatter)

            binding.dateET.setText(localDate.toString())
            convertDateToSaveFormat(localDate.toString())
        }
    }

    private fun buildListeners() {
        binding.dateET.setOnClickListener {
            showDatePickerDialog()
        }

        binding.updateB.setOnClickListener {
            reformattedStr?.let {
                userModel?.let { user ->
                    manageUserViewModel.updateUser(
                        UserModel(
                            user.id,
                            binding.nameET.text.toString(),
                            it
                        )
                    )
                }
            }
        }

        binding.saveB.setOnClickListener {
            if (!reformattedStr.isNullOrEmpty() && binding.nameET.text.toString().isNotEmpty()
            ) {
                manageUserViewModel.updateUser(
                    UserModel(
                        0,
                        binding.nameET.text.toString(),
                        reformattedStr!!
                    )
                )
            }
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance(
                localDate?.year ?: Calendar.getInstance().get(Calendar.YEAR),
                localDate?.monthValue ?: Calendar.getInstance().get(Calendar.MONTH) + 1,
                localDate?.dayOfMonth ?: Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ) { _, year, month, day ->
                // +1 because January is zero
                val selectedDate = day.toString() + "-" + (month + 1) + "-" + year

                binding.dateET.setText(selectedDate)

                convertDateToSaveFormat(selectedDate)

            }

        newFragment.show(requireActivity().supportFragmentManager, "datePicker")

    }

    private fun convertDateToSaveFormat(selectedDate: String) {
        val originalFormat = SimpleDateFormat("dd-MM-yyyy")
        val destinyFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        reformattedStr =
            destinyFormat.format(originalFormat.parse(selectedDate))

    }
}