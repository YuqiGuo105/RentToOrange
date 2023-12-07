package com.example.renttoorange.view.MainFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.renttoorange.R
import com.example.renttoorange.dao.RentInfoRepository
import com.example.renttoorange.model.HouseType
import com.example.renttoorange.model.RentInfo
import com.example.renttoorange.view.Adapters.CategoryAdapter
import com.example.renttoorange.view.Adapters.RentInfoAdapter

class CategoryFragment : Fragment() {
    private lateinit var rentInfoRecyclerView: RecyclerView
    private val rentInfoRepository = RentInfoRepository()
    private var selectedCategory: HouseType? = null // null represents
    private val categories = listOf("ALL", "APARTMENT", "HOUSE", "STUDIO")
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        // Set up the category RecyclerView
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view)
        categoryRecyclerView.layoutManager = LinearLayoutManager(activity)
        categoryRecyclerView.adapter = CategoryAdapter(categories) { categoryString ->
            val category = if (categoryString == "ALL") null else HouseType.valueOf(categoryString)
            // Change the category based on selection
            changeCategory(category)
        }

        // Initialize rent info RecyclerView
        rentInfoRecyclerView = view.findViewById(R.id.rent_info_recycler_view)
        rentInfoRecyclerView.layoutManager = LinearLayoutManager(activity)
        rentInfoRecyclerView.adapter = RentInfoAdapter(listOf())

        // Set the default category to 'ALL'
        changeCategory(null)
        
        return view
    }

    fun changeCategory(newCategory: HouseType?) {
        selectedCategory = newCategory
        rentInfoRepository.retrieveAllRentInfo { rentInfoList ->
            rentInfoList?.let {
                val filteredList = selectedCategory?.let { category ->
                    it.filter { rentInfo -> rentInfo.category == category }
                } ?: it // If 'null' (ALL), no filtering is applied
                rentInfoRecyclerView.adapter = RentInfoAdapter(filteredList)
            }
        }
    }

}