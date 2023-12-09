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
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.renttoorange.R
import com.example.renttoorange.dao.AdRepository
import com.example.renttoorange.dao.RentInfoRepository
import com.example.renttoorange.dao.UserRepository
import com.example.renttoorange.model.Ad
import com.example.renttoorange.model.RentInfo
import com.example.renttoorange.view.Adapters.AdBannerAdapter
import com.example.renttoorange.view.Adapters.RentInfoAdapter
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class HomePageFragment : Fragment() {
    private lateinit var toolbarProfileImageView: ImageView
    private lateinit var drawerProfileImageView: ImageView
    private lateinit var drawerUsernameTextView: TextView

    // Declare the profileImageView as nullable and initialize it later
    private var profileImageView: ImageView? = null

    private lateinit var adViewPager: ViewPager2
    private lateinit var rentalRecyclerView: RecyclerView

    private lateinit var auth: FirebaseAuth
    private lateinit var userRepository: UserRepository

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView

    override fun onAttach(context: Context) {
        super.onAttach(context)

        auth = FirebaseAuth.getInstance()
        userRepository = UserRepository(auth)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        // Initialize your toolbar here
        toolbar = view.findViewById(R.id.toolbar)

        drawerLayout = view.findViewById(R.id.drawer_layout)
        navigationView = view.findViewById(R.id.nav_view)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)

        // Initialize the ImageView in the top bar
        toolbarProfileImageView = toolbar.findViewById(R.id.imageView_profile)

        // Initialize views in the drawer
        val headerView = navigationView.getHeaderView(0)
        drawerProfileImageView = headerView.findViewById(R.id.imageView_header_profile) // The ID of your ImageView in the nav_header_main.xml
        drawerUsernameTextView = headerView.findViewById(R.id.textView_header_username) // The ID of your TextView in the nav_header_main.xml

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        adViewPager = view.findViewById(R.id.viewpager_ad_banner)
//        rentalRecyclerView = view.findViewById(R.id.recyclerview_rental_info)

        // Set up the toolbar
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        // Set up click listener for profile ImageView to open the drawer
        profileImageView?.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Set up the NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle navigation view item clicks here.
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle the home action
                }
                // Handle other menu item clicks
            }
            // Close the navigation drawer
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        toolbar.let {
            // Do something with the toolbar
        }

        loadUserInfo()
//        setupRentalRecyclerView()
//        setupAdBanner()
    }

    private fun loadUserInfo() {
        val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences?.getString("userEmail", null)

        userEmail?.let {
            userRepository.fetchUserInfo { user ->
                user?.let {
                    // Load user profile image into both the toolbar and drawer header using Picasso
                    Picasso.get().load(user.image).into(toolbarProfileImageView)
                    Picasso.get().load(user.image).into(drawerProfileImageView)

                    // Set username in drawer header
                    drawerUsernameTextView.text = user.username
                } ?: run {
                    // Handle the case when the user is null (not found or not logged in)
                }
            }
        } ?: run {
            // Handle the case when userEmail is null (no email stored in shared preferences)
        }
    }


//    private fun setupAdBanner() {
//        getAds { adList ->
//            // Set up the ViewPager2 adapter for the ad banner
//            val adapter = if (adList.isNotEmpty()) {
//                AdBannerAdapter(adList)
//            } else {
//                // Handle the case where no ads are available
//                val placeholderAd = Ad(
//                    title = "No Ads Available",
//                    address = "No Address Available",
//                    description = "Check back later for more ads!",
//                    imageUrl = "https://img.freepik.com/premium-photo/modern-abstract-background_694282-896.jpg"
//                )
//                AdBannerAdapter(listOf(placeholderAd))
//            }
//            adViewPager.adapter = adapter
//        }
//    }
//    //
//    private fun setupRentalRecyclerView() {
//        getRentalInfo { rentInfoList ->
//            // Set up the RecyclerView adapter for rental information
//            rentalRecyclerView.layoutManager = GridLayoutManager(context, 2)
//            rentalRecyclerView.adapter = RentInfoAdapter(rentInfoList)
//        }
//    }
//
//    private fun getAds(callback: (List<Ad>) -> Unit) {
//        val adRepository = AdRepository() // Assuming you have an AdRepository class similar to RentInfoRepository
//
//        adRepository.retrieveAllAds { adList ->
//            // Check if adList is not null
//            if (adList != null) {
//                // Do any additional processing if needed
//                // For now, just pass the adList to the callback
//                callback(adList)
//            } else {
//                // Handle the case where retrieving ads failed
//                callback(emptyList()) // or callback(null) depending on how you want to handle it
//            }
//        }
//    }
//
//    //
//    private fun getRentalInfo(callback: (List<RentInfo>) -> Unit) {
//        val rentInfoRepository = RentInfoRepository()
//
//        rentInfoRepository.retrieveAllRentInfo { rentInfoList ->
//            // Check if rentInfoList is not null
//            if (rentInfoList != null) {
//                // Do any additional processing if needed
//                // For now, just pass the rentInfoList to the callback
//                callback(rentInfoList)
//            } else {
//                // Handle the case where retrieving rental info failed
//                callback(emptyList()) // or callback(null) depending on how you want to handle it
//            }
//        }
//    }
}