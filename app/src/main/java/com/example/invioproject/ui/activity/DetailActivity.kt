package com.example.invioproject.ui.activity

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.invioproject.Util.ApiState
import com.example.invioproject.ViewModel.CharacterViewModel
import com.example.invioproject.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityRetainedScoped



@ActivityRetainedScoped
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: CharacterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("value_id")
        viewModel.getCharacter(id.toString())
        setupGetCharacter()

        // showing the back button in action bar
        val actionBar: ActionBar? = supportActionBar
        // showing the back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    @SuppressLint("SetTextI18n")
    private fun setupGetCharacter() {
        lifecycleScope.launchWhenStarted {
            viewModel._characterStateFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
                        binding.txtCharacterName.isVisible = false
                    }
                    is ApiState.Failure -> {
                        binding.txtCharacterName.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.CharacterSucess -> {

                        binding.txtCharacterName.isVisible = true
                        binding.txtCharacterName.text = it.data.name

                        binding.txtCharacterStatus.text = "Status :     "+ it.data.status
                        binding.txtCharacterSpecy.text = "Specie :      "+ it.data.species
                        binding.txtCharacterGender.text = "Gender :       " +it.data.gender
                        binding.textCharacterOrigin.text = "Origin :       " +it.data.origin.name
                        binding.textCharacterLocation.text = "Location :     " +it.data.location.name
                        binding.textCharacterEpisodes.text = "Episodes :     " +it.data.episode.size.toString()
                        binding.characterCreated.text = "Created :     " +it.data.created







                        Glide.with(this@DetailActivity)
                            .load(it.data.image)
                            .centerCrop()
                            .into(binding.imageViewCharacterDetail)
                    }
                    is ApiState.Empty -> {
                        Toast.makeText(
                            this@DetailActivity, "Este campo esta vacio", Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {}
                }
            }


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}