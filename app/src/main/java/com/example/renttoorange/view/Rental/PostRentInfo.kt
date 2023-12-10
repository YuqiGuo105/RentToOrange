package com.example.renttoorange.view.Rental

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.example.renttoorange.R
import com.example.renttoorange.dao.ImageUploader
import com.example.renttoorange.dao.RentInfoRepository
import com.example.renttoorange.model.HouseType
import com.example.renttoorange.model.RentInfo
import com.example.renttoorange.view.Homepage

class PostRentInfo : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etAddress: EditText
    private lateinit var etLandlordInfo: EditText
    private lateinit var etRent: EditText
    private lateinit var spinnerHouseType: Spinner

    private val imageUploader = ImageUploader()
    private val rentInfoRepository = RentInfoRepository()
    private var selectedImageUri: Uri? = null

    private lateinit var imgPreview: ImageView

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
        imgPreview = findViewById<ImageView>(R.id.imgPreview)


        // Populate the spinner
        val houseTypeAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, HouseType.values())
        houseTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerHouseType.adapter = houseTypeAdapter

        btnSelectImage.setOnClickListener { selectImageFromGallery() }
        btnSubmitRentInfo.setOnClickListener { postRentInfo() }
    }

    private fun postRentInfo() {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()
        val address = etAddress.text.toString()
        val landlordInfo = etLandlordInfo.text.toString()
        val rent = etRent.text.toString()
        val selectedHouseType = spinnerHouseType.selectedItem as HouseType

        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null) ?: ""

        selectedImageUri?.let { uri ->
            imageUploader.uploadImage(uri) { imageUrl ->
                if (imageUrl != null) {
                    val rentInfo = RentInfo(
                        title = title,
                        description = description,
                        address = address,
                        imageUrl = imageUrl,
                        landlordInfo = landlordInfo,
                        rent = rent,
                        category = selectedHouseType,
                        postedUserId = userId
                    )

                    rentInfoRepository.saveRentInfo(rentInfo) { isSuccessful ->
                        if (isSuccessful) {
                            showToast("Rent Info Posted Successfully")

                            // Using a Handler to post a delayed action.
                            Handler(Looper.getMainLooper()).postDelayed({
                                // Code to navigate back to the User Account Fragment.
                                // Assuming you're using a NavController to handle fragment transactions.
                                val back = Intent(this, Homepage::class.java)
                                startActivity(back)
                                finish()

                            }, 5000) // 5000 milliseconds delay for 5 seconds.
                        } else {
                            showToast("Failed to Post Rent Info")
                        }
                    }
                } else {
                    showToast("Failed to Upload Image")
                }
            }
        } ?: showToast("No Image Selected")
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            imgPreview.setImageURI(selectedImageUri)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

}