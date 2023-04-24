package com.example.invioproject.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.invioproject.Adapter.LocationAdapter
import com.example.invioproject.Adapter.RickAdapter
import com.example.invioproject.Util.ApiState
import com.example.invioproject.ViewModel.MainViewModel
import com.example.invioproject.databinding.FragmentMainBinding
import com.example.invioproject.ui.activity.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var rickAdapter: RickAdapter
    private lateinit var locationAdapter: LocationAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val page = arguments?.getString("message")
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        initRecyclerview()
        if (page != null) {
            viewModel.getPost(page)
            viewModel.getLocation(page)
        }else{
            viewModel.getPost("1")
            viewModel.getLocation("1")
        }

        launchObserver(viewModel)

        return binding.root
    }

    private fun initRecyclerview() {
        rickAdapter = RickAdapter(ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = rickAdapter


            rickAdapter.onItemClick = { character ->
                goToDetailScreen(character)
            }
        }
    }



    private fun goToDetailScreen(value: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("value_id", value)
        startActivity(intent)
    }

    private fun launchObserver(viewModel: MainViewModel) {
        lifecycleScope.launchWhenStarted {
            viewModel._postStateFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
                        binding.recyclerview.isVisible = false
                        binding.recyclerviewHorizontal.isVisible  = false
                        binding.progressBar.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.recyclerviewHorizontal.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        rickAdapter.setData(it.data)
                        rickAdapter.notifyDataSetChanged()
                    }
                    is ApiState.LocationSucess -> {
                        binding.recyclerviewHorizontal.isVisible = true
                        binding.progressBar.isVisible = false
                        locationAdapter.setData(it.data)
                        locationAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty -> {
                        Toast.makeText(context, "Este campo esta vacio", Toast.LENGTH_LONG).show()
                    }

                    else -> {}
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}