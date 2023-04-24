package com.example.invioproject.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.invioproject.R
import com.example.invioproject.ViewModel.MainViewModel
import com.example.invioproject.databinding.ActivityMainBinding
import com.example.invioproject.ui.fragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityRetainedScoped


@ActivityRetainedScoped
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val number: Int = 1
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(MainFragment(), number.toString())


    }

    private fun loadFragment(fragment: Fragment, number: String) {

        val bundle = Bundle()
        bundle.putString("message", number)
        fragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_main_container, fragment)
        fragmentTransaction.commit()
    }


}