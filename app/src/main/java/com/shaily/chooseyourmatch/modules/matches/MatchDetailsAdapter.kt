package com.shaily.chooseyourmatch.modules.matches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shaily.chooseyourmatch.databinding.ChooseYourMatchItemBinding
import com.shaily.chooseyourmatch.models.Results

class MatchDetailsAdapter :
    ListAdapter<Results, MatchDetailsAdapter.MatchDetailsViewHolder>(MatchResultsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchDetailsViewHolder {
        val binding =
            ChooseYourMatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchDetailsViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MatchDetailsViewHolder(private val binding: ChooseYourMatchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(matchResult: Results) {
            binding.apply {
                Glide.with(itemView)
                    .load(matchResult.picture.large)
                    .into(imageView)

                tvFullName.text = matchResult.name.first
                tvAge.text = matchResult.dob.age.toString()
                tvMobileNo.text = matchResult.cell
                tvGender.text = matchResult.gender
                val locationText:String = matchResult.location.city
                var res: String? = ""
                for (i in locationText.indices) {
                    val ch: Char = locationText.toCharArray()[i]
                    if (Character.isUpperCase(ch)) res += " " + Character.toLowerCase(ch) else res += ch
                }
                tvCity.text = res

                btnDecline.setOnClickListener {
                    onButtonDeclineClickListener?.let { it(matchResult) }
                }

                btnAccept.setOnClickListener {
                    btnAccept.visibility = View.INVISIBLE
                    btnDecline.visibility = View.INVISIBLE
                    onButtonAcceptClickListener?.let { it(matchResult) }
                }
            }
        }
    }

    class MatchResultsComparator : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results) =
            oldItem.cell == newItem.cell


        override fun areContentsTheSame(oldItem: Results, newItem: Results) =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, MatchResultsComparator())

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onButtonDeclineClickListener: (((Results) -> Unit))? = null

    fun setDeclineOnClickListener(listener: (Results) -> Unit) {
        onButtonDeclineClickListener = listener
    }

    private var onButtonAcceptClickListener: ((Results) -> Unit)? = null

    fun setAcceptOnClickListener(listener: (Results) -> Unit) {
        onButtonAcceptClickListener = listener
    }
}