package com.shaily.chooseyourmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaily.chooseyourmatch.data.MatchDetailsResponse
import com.shaily.chooseyourmatch.databinding.ActivityChooseMatchBinding
import com.shaily.chooseyourmatch.modules.matches.MatchDetailsAdapter
import com.shaily.chooseyourmatch.util.Resource
import com.shaily.chooseyourmatch.viewmodel.ChooseMatchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseMatchActivity : AppCompatActivity() {

    private val viewModel: ChooseMatchViewModel  by viewModels()

    private  val TAG = "ChooseMatchActivity"

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

            viewModel.matchResultsLiveData.observe(this@ChooseMatchActivity) {
                matchDetailsReponse -> matchAdapter.differ.submitList(matchDetailsReponse.data?.results)
            }

        }


    }
}

//observe(this@ChooseMatchActivity) {
//                matchResults -> matchAdapter.differ.submitList(matchResults.data)
//            }

//viewModel.matchResultsLiveData.observe(this@ChooseMatchActivity, Observer {response ->
//    when(response) {
//        is Resource.Success -> {
//            response.data?.let { matchResultsResponse ->
//                matchAdapter.differ.submitList(matchResultsResponse.results)
//            }
//        }
//        is Resource.Error -> {
//            response.message?.let { message ->
//                Log.e(TAG, "onCreate: An error occured $message")
//            }
//        }
//        else -> Log.e(TAG, "onCreate: A random error occured")
//    }
//
//})