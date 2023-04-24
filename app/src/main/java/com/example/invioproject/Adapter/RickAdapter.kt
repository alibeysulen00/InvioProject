package com.example.invioproject.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.invioproject.R
import com.example.invioproject.databinding.EachRowBinding
import com.example.invioproject.modelPost.Result

class RickAdapter(private var postList: List<Result>)
    : RecyclerView.Adapter<RickAdapter.PostViewHolder>() {



    private lateinit var binding: EachRowBinding

    var onItemClick: ((positionString: String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {


        if(postList[position].gender=="Female"){
            binding.genderImage.setImageResource(R.drawable.female)

        }
        else if(postList[position].gender == "unknown"){
            binding.genderImage.setImageResource(R.drawable.unknown)
        }
        else{
            binding.genderImage.setImageResource(R.drawable.male)
        }




        binding.txtName.text=postList[position].name




        Glide.with(holder.itemView.context)
            .load(postList[position].image)
            .centerCrop()
            .into(binding.imageCharacterRow)

        //itemClick
        val id = postList[position].id
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(id.toString())
        }

    }

    override fun getItemCount(): Int = postList.size

    class PostViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(postList: List<Result>) {
        this.postList=postList
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }


}
