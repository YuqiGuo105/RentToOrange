// AdRepository.kt
package com.example.renttoorange.dao

import com.example.renttoorange.model.Ad

class AdRepository {

    // Retrieve dummy ads directly in the function
    fun retrieveAllAds(callback: (List<Ad>?) -> Unit) {
        val dummyAds = listOf(
            Ad("1", "Norstar Apartments","4784 Norstar Blvd, Liverpool, NY 13088", "\"With downtown Syracuse just 15 minutes away, Norstar Apartments provides a quiet and welcoming community in scenic Liverpool, NY. With 1-, 2-, and 3-bedroom floorplan options that include carpeted floors, ceiling fans, and fully equipped kitchens with all-electric appliances, you’ll find Norstar a family-friendly community. With community amenities that include a swimming pool, clubhouse, and playground, Norstar is a peaceful and well-kept community.\"\n", "https://www.norstarapartments.com/wp-content/uploads/sites/37/2023/06/0O7A6258-1.jpg.webp"),
            Ad("2", "Theory Syracuse", "919 E Genesee St, Syracuse, NY 13210","Enhance your experience by living at Theory Syracuse! Our community has everything you need for your busy lifestyle; from fully furnished modern apartments to a vibrant community that boasts resort-style amenities. Just a few blocks from campus, restaurants, and more; our housing raises the bar.", "https://theorysyracuse.com/wp-content/uploads/2023/05/home-3b.jpg"),
            Ad("3", "Clarendon Heights","508 Ivy Ridge Rd, Syracuse, NY 13210", "Clarendon Heights is the perfect place to call home. Located just minutes from downtown Syracuse and Syracuse University, we feature spacious, updated apartments with gorgeous views surrounding the city of Syracuse. Choose from several different styles of 1 or 2 bedroom apartment floor plans. Situated in a private location tucked away from the bustling city life, it is the perfect location for those wanting to be near all the city has to offer without the hassles.", "https://images1.apartments.com/i2/KAc5Cr6RFSwDWIygxLTQNejRy0ETQ9C0Rjbg2mKWec8/111/clarendon-heights-syracuse-ny-primary-photo.jpg")
        )

        callback(dummyAds)
    }
}
