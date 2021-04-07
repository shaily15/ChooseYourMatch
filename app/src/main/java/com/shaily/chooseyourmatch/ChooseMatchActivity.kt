package com.shaily.chooseyourmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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


            matchAdapter.setDeclineOnClickListener { results ->
                viewModel.deleteMatch(results)
                Toast.makeText(this@ChooseMatchActivity, "Member Declined", Toast.LENGTH_SHORT)
                    .show()
            }

            matchAdapter.setAcceptOnClickListener { results ->
                viewModel.saveMatch(results)
                Toast.makeText(this@ChooseMatchActivity, "Member Accepted", Toast.LENGTH_SHORT)
                    .show()
            }

            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val result = matchAdapter.differ.currentList[position]
                    viewModel.deleteMatch(result)
                    Snackbar.make(recyclerView, "Successfully deleted match", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo") {
                            viewModel.saveMatch(result)
                        }
                        show()
                    }
                }
            }

            ItemTouchHelper(itemTouchHelperCallback).apply {
                attachToRecyclerView(recyclerView)
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