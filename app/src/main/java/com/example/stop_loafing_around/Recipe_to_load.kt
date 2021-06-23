package com.example.stop_loafing_around

import android.net.Uri

object Recipe_to_load {
    var recipe_name = ""
    var preview_image: Uri? = null
    var recipes_list = arrayListOf<String>()
    var ingredients = arrayListOf<String>(
    )
    var steps_array = arrayListOf<One_step>(
    )
    fun reset(){
        preview_image = null
        ingredients = arrayListOf<String>(
        )
        steps_array = arrayListOf<One_step>(
        )
    }
}