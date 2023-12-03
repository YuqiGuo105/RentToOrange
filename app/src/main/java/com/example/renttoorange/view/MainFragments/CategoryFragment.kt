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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        rentInfoRecyclerView = view.findViewById(R.id.rentInfoRecyclerView)
        rentInfoRecyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize buttons and set click listeners
        view.findViewById<Button>(R.id.all).setOnClickListener { changeCategory(null) }
        view.findViewById<Button>(R.id.btnApartment).setOnClickListener { changeCategory(HouseType.APARTMENT) }
        view.findViewById<Button>(R.id.btnHouse).setOnClickListener { changeCategory(HouseType.HOUSE) }


        rentInfoRepository.retrieveAllRentInfo { rentInfoList ->
            rentInfoList?.let {
                val filteredList = it.filter { rentInfo -> rentInfo.category == selectedCategory }
                rentInfoRecyclerView.adapter = RentInfoAdapter(filteredList)
            } ?: run {
                // Handle the error scenario
            }
        }


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