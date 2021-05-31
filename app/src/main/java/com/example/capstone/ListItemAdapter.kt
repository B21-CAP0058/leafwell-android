package com.example.capstone

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.capstone.data.source.local.entity.HerbListEntity
import com.example.capstone.databinding.ListItemBinding
import com.example.capstone.ui.detail.DetailActivity

class ListItemAdapter: RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {
    private var listItems = ArrayList<HerbListEntity>()
    class ListItemViewHolder(private var binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: HerbListEntity) {
            with(binding){
                tvTitle.text = item.name
                 Glide.with(itemView)
                     .load(item.image)
                     .transform(RoundedCorners(10))
                     .apply(RequestOptions.overrideOf(150,180))
                     .into(ivPoster)
            }

            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID,item.uuid)
                itemView.context.startActivity(intent)
            }
        }

    }

    fun setItem(items:List<HerbListEntity>){
        if(items.isNullOrEmpty())return
        this.listItems.clear()
        this.listItems.addAll(items)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListItemViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListItemViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = listItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}