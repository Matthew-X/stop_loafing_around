package com.example.stop_loafing_around

import android.net.Uri

object Recipe_to_load {
    var recipe_name = ""
    var preview_image: Uri? = null
    var ingredients = arrayListOf<String>(
        ""
    )
    var steps_array = arrayListOf<One_step>(
        One_step()
    )
}