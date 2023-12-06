package com.example.renttoorange.view.MainFragments

import User
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.renttoorange.R
import com.example.renttoorange.dao.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class HomePageFragment : Fragment() {
    private lateinit var usernameTextView: TextView
    private lateinit var profileImageView: ImageView
    private lateinit var adViewPager: ViewPager2
    private lateinit var rentalRecyclerView: RecyclerView

    private lateinit var auth: FirebaseAuth
    private lateinit var userRepository: UserRepository

    override fun onAttach(context: Context) {
        super.onAttach(context)

        auth = FirebaseAuth.getInstance()
        userRepository = UserRepository(auth)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameTextView = view.findViewById(R.id.textview_username)
        profileImageView = view.findViewById(R.id.imageview_profile)
        adViewPager = view.findViewById(R.id.viewpager_ad_banner)
        rentalRecyclerView = view.findViewById(R.id.recyclerview_rental_info)

        loadUserInfo()

    }

    private fun loadUserInfo() {
        val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences?.getString("userEmail", null)

        userEmail?.let {
            userRepository.fetchUserInfo { user ->
                user?.let {
                    updateUI(it)
                } ?: run {
                    // Handle the case when the user is null (not found or not logged in)
                }
            }
        } ?: run {
            // Handle the case when userEmail is null (no email stored in shared preferences)
        }
    }

    private fun updateUI(user: User) {
        usernameTextView.text = user.username

        Picasso.get()
            .load(user.image)
            .into(profileImageView)
    }


//    private fun setupAdBanner() {
//        // Set up the ViewPager2 adapter for the ad banner
//        adViewPager.adapter = AdBannerAdapter(getAds())
//        // You would need to implement the logic to retrieve and adapt ads
//    }
//
//    private fun setupRentalRecyclerView() {
//        // Set up the RecyclerView adapter for rental information
//        rentalRecyclerView.layoutManager = LinearLayoutManager(context)
//        rentalRecyclerView.adapter = RentalInfoAdapter(getRentalInfo())
//        // You would need to implement the logic to retrieve and adapt rental info
//    }
//
//    // Dummy functions to simulate getting ads and rental info
//    private fun getAds(): List<Ad> {
//        // Replace with actual ad retrieval logic
//        return listOf()
//    }
//
//    private fun getRentalInfo(): List<RentalInfo> {
//        // Replace with actual rental info retrieval logic
//        return listOf()
//    }

}