package com.shaily.chooseyourmatch.modules.matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shaily.chooseyourmatch.data.MatchDetailsResponse
import com.shaily.chooseyourmatch.data.Results
import com.shaily.chooseyourmatch.databinding.ChooseYourMatchItemBinding

class MatchDetailsAdapter : ListAdapter<Results, MatchDetailsAdapter.MatchDetailsViewHolder>(MatchResultsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchDetailsViewHolder {
        val binding = ChooseYourMatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchDetailsViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        if(currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MatchDetailsViewHolder(private val binding: ChooseYourMatchItemBinding) :
            RecyclerView.ViewHolder(binding.root){

        fun bind(matchResult: Results) {
            binding.apply {
                Glide.with(itemView)
                    .load(matchResult.picture.large)
                    .into(imageView)

                val fullName: String = matchResult.name.first + matchResult.name.last
                tvFullName.text = fullName
                tvAge.text = matchResult.dob.age.toString()
                tvMobileNo.text = matchResult.cell
                tvGender.text = matchResult.gender
                tvCity.text = matchResult.location.city
                tvState.text = matchResult.location.state
                tvCountry.text = matchResult.location.country



            }
        }
    }

    class MatchResultsComparator : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results) =
            oldItem.id.name == newItem.id.name


        override fun areContentsTheSame(oldItem: Results, newItem: Results) =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, MatchResultsComparator())

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}