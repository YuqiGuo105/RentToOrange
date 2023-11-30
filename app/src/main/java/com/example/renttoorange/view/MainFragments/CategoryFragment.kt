package com.example.renttoorange.view.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.renttoorange.R
import com.example.renttoorange.view.Adapters.CategoryAdapter

class CategoryFragment : Fragment() {
    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoryAdapter
    private var categories: List<Category> = listOf() // Initialize with an empty list

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesRecyclerView = view.findViewById(R.id.recyclerview_categories)
        categories = getCategories() // This should fetch the categories from your database or API

        // Ensure the adapter is initialized before the layout pass
        categoriesAdapter = CategoryAdapter(categories)
        categoriesRecyclerView.adapter = categoriesAdapter
        categoriesRecyclerView.layoutManager = GridLayoutManager(context, 2) // Display categories in a 2-column grid
    }


    private fun getCategories(): List<Category> {
        // This method should return a list of Category objects
        // For now, let's return a placeholder list
        return listOf(
            Category("Apartments"),
            Category("Houses"),
            Category("Studios"),
            // Add more categories as needed
        )
    }
}

data class Category(val name: String)