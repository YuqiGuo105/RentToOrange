package com.example.renttoorange.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.renttoorange.model.RentInfo
import com.example.renttoorange.R
import com.squareup.picasso.Picasso

class RentInfoAdapter(private val rentInfoList: List<RentInfo>) : RecyclerView.Adapter<RentInfoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.textViewTitle)
        val textViewPrice: TextView = view.findViewById(R.id.textViewPrice)
        val imageImageView: ImageView = view.findViewById(R.id.imageViewRent)

        fun bind(rentInfo: RentInfo) {
            titleTextView.text = rentInfo.title
            textViewPrice.text = "$${rentInfo.rent}"
            if (rentInfo.imageUrl != null && rentInfo.imageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(rentInfo.imageUrl)
                    .into(imageImageView)
            } else {
                Picasso.get()
                    .load("https://firebasestorage.googleapis.com/v0/b/renttoorange-5f4fc.appspot.com/o/rentInfo%2Ferror.png?alt=media&token=7b41c24d-a757-44b6-8118-dd28dbff4df4")
                    .into(imageImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rent_info_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rentInfoList[position])
    }

    override fun getItemCount() = rentInfoList.size

}

