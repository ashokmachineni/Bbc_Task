package com.android.apptask.ui.main.fruits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.apptask.R
import com.android.apptask.databinding.FragmentFruitsBinding
import com.android.apptask.ui.main.FruitsViewModel
import com.android.apptask.utils.OnClick
import com.android.apptask.utils.gone
import com.android.apptask.utils.launchViewLifecycleScope
import com.android.apptask.utils.show
import com.android.apptask.domain.models.Fruit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlin.properties.Delegates

@AndroidEntryPoint
class FruitsFragment : Fragment() {

    private var startTime by Delegates.notNull<Long>()

    private var endTime by Delegates.notNull<Long>()

    private lateinit var viewModel: FruitsViewModel

    private var binding: FragmentFruitsBinding? = null

    private var fruitsAdapter: FruitsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTime = System.currentTimeMillis()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel = ViewModelProvider(requireActivity()).get(FruitsViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fruits, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRefreshLayout()

        fetchFruits()

    }

    private fun initRefreshLayout() {

        binding?.refresh?.setOnRefreshListener {
            binding?.refresh?.isRefreshing = true
            fetchFruits(false)
        }

    }

    private fun fetchFruits(loading: Boolean = true) {
        launchViewLifecycleScope {

            if (loading) {
                binding?.loadingLayout?.loadingLayout?.show()
            }

            val response = viewModel.getFruits().first()

            viewModel.logRequestTime(response.requestTime).first()

            response.onSuccess { fruitsResponse ->

                binding?.refresh?.isRefreshing = false
                binding?.loadingLayout?.loadingLayout?.gone()

                try {
                    initFruitsUI(fruitsResponse.fruit!!)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), R.string.try_again_later, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.logError(e.message.toString())
                }

                logScreenTime()

            }

            response.onFailure { message, code ->

                binding?.refresh?.isRefreshing = false
                binding?.loadingLayout?.loadingLayout?.gone()
                Toast.makeText(requireContext(), R.string.try_again_later, Toast.LENGTH_SHORT)
                    .show()

                viewModel.logError(message)
                logScreenTime()

            }

        }
    }

    private fun logScreenTime() {
        endTime = System.currentTimeMillis()
        viewModel.logScreenTime(viewModel.calculateDuration(startTime, endTime))
    }

    private fun initFruitsUI(fruits: List<Fruit>) {
        binding?.fruits?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            fruitsAdapter = FruitsAdapter(fruits, onFruitClick)
            adapter = fruitsAdapter
        }
    }

    private val onFruitClick = OnClick<Fruit> { fruit, _ ->

        val action = FruitsFragmentDirections.actionFruitsFragmentToFruitDetailsFragment(
            fruit.type.toString(),
            fruit.price.toString(),
            fruit.weight.toString(),
        )

        findNavController().navigate(action)

    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding?.fruits?.adapter = null
        binding = null

    }

}