package com.example.invioproject.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.invioproject.R
import com.example.invioproject.databinding.EachRowBinding
import com.example.invioproject.databinding.HorizontalRowBinding
import com.example.invioproject.modelLocation.ResultLocation
import com.example.invioproject.modelPost.Result

class LocationAdapter(private var postList: List<ResultLocation>)
    : RecyclerView.Adapter<LocationAdapter.PostViewHolder>() {



    private lateinit var binding: HorizontalRowBinding

    var onItemClick: ((positionString: String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = HorizontalRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {


        binding.button.setText(postList[position].name)

        //itemClick
        val id = postList[position].id
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(id.toString())
        }

    }

    override fun getItemCount(): Int = postList.size

    class PostViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(postList: List<ResultLocation>) {
        this.postList=postList
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }


}
