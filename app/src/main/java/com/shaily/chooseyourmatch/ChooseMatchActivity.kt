package com.shaily.chooseyourmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaily.chooseyourmatch.databinding.ActivityChooseMatchBinding
import com.shaily.chooseyourmatch.modules.matches.MatchDetailsAdapter
import com.shaily.chooseyourmatch.util.Resource
import com.shaily.chooseyourmatch.viewmodel.ChooseMatchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseMatchActivity : AppCompatActivity() {

    private val viewModel: ChooseMatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChooseMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val matchAdapter = MatchDetailsAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = matchAdapter
                layoutManager = LinearLayoutManager(this@ChooseMatchActivity)
            }

            matchAdapter.setDeclineOnClickListener {
                Toast.makeText(this@ChooseMatchActivity, "Member Declined", Toast.LENGTH_SHORT)
                    .show()
            }

            matchAdapter.setAcceptOnClickListener {
                Toast.makeText(this@ChooseMatchActivity, "Member Accepted", Toast.LENGTH_SHORT)
                    .show()
            }

            viewModel.matchResultsLiveData.observe(this@ChooseMatchActivity) { result ->
                matchAdapter.differ.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }
        }
    }
}