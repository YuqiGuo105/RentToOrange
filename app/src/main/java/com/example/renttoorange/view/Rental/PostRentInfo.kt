package com.example.renttoorange.view.Rental

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.example.renttoorange.R
import com.example.renttoorange.model.HouseType
import com.example.renttoorange.model.RentInfo
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID

class PostRentInfo : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etAddress: EditText
    private lateinit var etLandlordInfo: EditText
    private lateinit var etRent: EditText
    private var imageUri: Uri? = null
    private lateinit var spinnerHouseType: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_rent_info)

        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        etAddress = findViewById(R.id.etAddress)
        etLandlordInfo = findViewById(R.id.etLandlordInfo)
        etRent = findViewById(R.id.etRent)
        spinnerHouseType = findViewById(R.id.spinnerHouseType)

        val btnSelectImage = findViewById<Button>(R.id.btnSelectImage)
        val btnSubmitRentInfo = findViewById<Button>(R.id.btnSubmitRentInfo)
        val imgPreview = findViewById<ImageView>(R.id.imgPreview)


        // Populate the spinner
        val houseTypeAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, HouseType.values())
        houseTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerHouseType.adapter = houseTypeAdapter

    }



}