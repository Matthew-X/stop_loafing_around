package com.example.stop_loafing_around

import android.net.Uri

object Recipe_to_load {
    var recipe_name = ""
    var preview_image: Uri? = null
    var preview_images = arrayListOf<Uri?>()
    var recipes_list = arrayListOf<String>()
    var ingredients = arrayListOf<String>()
    var steps_array = arrayListOf<One_step>()
    fun resetRecipePIS(){
        preview_image = null
        ingredients = arrayListOf<String>()
        steps_array = arrayListOf<One_step>()
    }
    fun resetRecipeALL(){
        recipe_name = ""
        preview_image = null
        preview_images = arrayListOf<Uri?>()
        ingredients = arrayListOf<String>()
        steps_array = arrayListOf<One_step>()
    }
}