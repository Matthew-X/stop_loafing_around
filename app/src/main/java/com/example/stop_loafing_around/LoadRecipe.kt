package com.example.stop_loafing_around

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class LoadRecipe {
    fun sendRecipeData(recipe_name:String){
        val db = Firebase.firestore
        val docRef = db.collection("recipe").document(recipe_name)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val recipe_steps = documentSnapshot.toObject<Recipe_to_load>()
        }
    }
}