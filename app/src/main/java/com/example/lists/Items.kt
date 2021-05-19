package com.example.lists

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed class Items : Parcelable {

    @Parcelize
    data class Company(
        var id: Long,
        val image: String,
        val name: String,
        val price: String,
        val descriptions: String,
        val location: String,
        val rating: Double
    ) : Items() {
        companion object {
            val reserveCompanyList = arrayListOf(
                Company(
                    id = 1,
                    image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/3df13e745120719d5ba56f4876b8fa61.png",
                    name = "Mercury Development",
                    price = "\$50 - \$99 / hr",
                    descriptions = "Full Stack Development, UX/UI, Testing, Quality Assurance, Business Analysis, Staff Augmentation, and Consulting",
                    location = "▽ Samara, Russia",
                    rating = 5.0
                ), Company(
                    id = 2,
                    image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/magora_sign_200px-.png",
                    name = "Magora",
                    price = "\$50 - \$99 / hr",
                    descriptions = "Magora is an IT consultant that offers digital transformation services to start-ups and enterprises",
                    location = "▽ St. Petersburg, Russia",
                    rating = 4.7
                ), Company(
                    id = 3,
                    image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/158064806d43071503ff578e453d660c.png",
                    name = "Orangesoft",
                    price = "\$50 - \$99 / hr",
                    descriptions = "We create great mobile and web apps using the latest technologies focused on building elegant and functional products.",
                    location = "▽ Moscow, Russia",
                    rating = 5.0
                ), Company(
                    id = 4,
                    image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/17884187_10154220930360448_3201341747770140275_n.png",
                    name = "Andersen",
                    price = "\$25 - \$49 / hr",
                    descriptions = "Offices worldwide — Western and Eastern Europe, the US, Australia",
                    location = "▽ St.Petersburg, Russia",
                    rating = 4.9
                ),
                Company(
                    id = 5,
                    image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/profile_photo512.png",
                    name = "nomtek",
                    price = "\$50 - \$99 / hr",
                    descriptions = "As a company, we focus mostly on mobile app design and development and also on Augmented and Mixed Reality products.",
                    location = "▽ Wroclaw, Poland",
                    rating = 4.9
                ),
                Company(
                    id = 6,
                    image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/5198bbb11bba925df59511218c449f4b.png",
                    name = "ChopDawg.com",
                    price = "$150 - $199 / hr",
                    descriptions = "Since 2009, we've partnered with startups and enterprises around the world to launch 350+ next-generation apps.",
                    location = "▽ Philadelphia, PA",
                    rating = 4.8
                ),
                Company(
                    id = 7,
                    image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/letterhead_logo.jpg",
                    name = "AppUnite",
                    price = "\$25 - \$49 / hr",
                    descriptions = "AppUnite specializes in creating highly-usable applications for Android, iOS and web apps in Elixir and Phoenix, React, Vue.js.",
                    location = "▽ Poznan, Poland",
                    rating = 4.9
                )
            )
        }
    }

    @Parcelize
    data class Clients(
        val id: Long,
        val image: String,
        val name: String,
        val descriptions: String,
        val rating: Double
    ) : Items() {
        companion object {
            val avatars = listOf(
                "https://www.pngjoy.com/pngm/186/3682484_harambe-face-sample-avatar-hd-png-download.png",
                "https://pickaface.net/gallery/avatar/20151109_144853_2380_sample.png",
                "https://pickaface.net/gallery/avatar/unr_sample_170130_2257_9qgawp.png",
                "https://pickaface.net/gallery/avatar/InspireBuddy5290e4bdc07a4.png"
            )
        }
    }

    @Parcelize
    data class CompanySquare(
        var id: Long,
        val image: String,
        val name: String,
        val rating: Double
    ) : Items()

    @Parcelize
    data class ClientsSquare(
        var id: Long,
        val image: String,
        val name: String,
        val rating: Double
    ) : Items()

    @Parcelize
    data class Loading(
        val id: Long
    ) : Items()
}


