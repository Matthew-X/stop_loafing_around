package com.example.stop_loafing_around

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class SendRecipe {
    fun sendRecipeData(){
        val db = Firebase.firestore
        val myRef = db.collection("recipe")
//        var stepsString = ""
//        for (i in 0 until Recipes.steps_array.size){
//            stepsString = stepsString.
//        }
        myRef.document().set(
            hashMapOf(
                "name" to Recipes.recipe_name,
                "ingredients" to JSONArray(Recipes.ingredients).toString(),
                "steps" to JSONArray(Recipes.steps_array).toString()
            )
        )
    }
}