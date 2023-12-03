package com.example.renttoorange.view.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        rentInfoRecyclerView = view.findViewById(R.id.rentInfoRecyclerView)
        rentInfoRecyclerView.layoutManager = LinearLayoutManager(context)

        rentInfoRepository.retrieveAllRentInfo { rentInfoList ->
            if (rentInfoList != null) {
                rentInfoRecyclerView.adapter = RentInfoAdapter(rentInfoList)
            } else {
                // Handle the error scenario
            }
        }
        return view
    }

}