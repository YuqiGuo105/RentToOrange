package com.example.renttoorange.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.renttoorange.model.Ad
import com.example.renttoorange.R
import com.squareup.picasso.Picasso

class AdBannerAdapter(private val ads: List<Ad>) : RecyclerView.Adapter<AdBannerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.txtview_ad_title)
        val imageImageView: ImageView = view.findViewById(R.id.imageview_ad)

        fun bind(ad: Ad) {
            titleTextView.text = ad.title
            if (ad.imageUrl != null && ad.imageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(ad.imageUrl)
                    .into(imageImageView)
            } else {
                Picasso.get()
                    .load("https://firebasestorage.googleapis.com/v0/b/renttoorange-5f4fc.appspot.com/o/rentInfo%2Ferror.png?alt=media&token=7b41c24d-a757-44b6-8118-dd28dbff4df4")
                    .into(imageImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ad_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ads[position])
    }

    override fun getItemCount() = ads.size
}
