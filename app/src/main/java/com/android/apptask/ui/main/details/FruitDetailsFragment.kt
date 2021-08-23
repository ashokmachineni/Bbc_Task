package com.android.apptask.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.apptask.R
import com.android.apptask.databinding.FragmentFruitDetailsBinding
import com.android.apptask.ui.main.FruitsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class FruitDetailsFragment : Fragment() {

    private var startTime by Delegates.notNull<Long>()
    private var endTime by Delegates.notNull<Long>()

    lateinit var viewModel: FruitsViewModel

    private var binding: FragmentFruitDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTime = System.currentTimeMillis()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fruit_details, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FruitsViewModel::class.java)

        initUI(
            arguments?.getString("type") ?: "",
            arguments?.getString("price") ?: "",
            arguments?.getString("weight") ?: "",
        )
        logScreenTime()

    }

    private fun initUI(
        type: String,
        price: String,
        weight: String
    ) {
        binding?.name?.text = type
        binding?.price?.text = price
        binding?.weight?.text = weight
    }

    private fun logScreenTime() {
        endTime = System.currentTimeMillis()
        viewModel.logScreenTime(viewModel.calculateDuration(startTime, endTime))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}