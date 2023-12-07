package com.example.renttoorange.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.renttoorange.R
import com.example.renttoorange.model.HouseType

class CategoryAdapter(
    private val categories: List<String>,
    private val onCategoryClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_view_category)
        val imageView: ImageView = view.findViewById(R.id.image_view_category_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_categories, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.textView.text = category
        // Set the image for the category. This is a placeholder. You'll set actual images based on the category.
        holder.imageView.setImageResource(when (category) {
            "APARTMENT" -> R.drawable.ic_apartment // Replace with your actual drawable resource
            "HOUSE" -> R.drawable.ic_home // Replace with your actual drawable resource
            "STUDIO" -> R.drawable.ic_studio // Replace with your actual drawable resource
            else -> R.drawable.ic_all // Replace with your actual drawable resource for "ALL"
        })
        holder.itemView.setOnClickListener { onCategoryClick(category) }
    }

    override fun getItemCount(): Int = categories.size
}