package com.example.stop_loafing_around

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class SendRecipe {
    fun sendRecipeData(){
        val db = Firebase.firestore
        val myRef = db.collection("recipe")
        var stepsString = ""
        var jObj:JSONObject? = null
        var jArr: JSONArray? = JSONArray()
        for (i in 0 until Recipes.steps_array.size){
            val step = JSONObject()

            step.put("step_no", "Step "+(i+1))
            step.put("step_desc", ""+Recipes.steps_array[i].description)
            step.put("img", ""+Recipes.steps_array[i].image)
            step.put("timer", ""+(Recipes.steps_array[i].timer[0].toString().toInt()*60*60+Recipes.steps_array[i].timer[1].toString().toInt()*60+Recipes.steps_array[i].timer[2].toString().toInt()))

            jArr?.put(step)

        }

        Log.d("Jarray", jArr.toString())
        myRef.document(Recipes.recipe_name).set(
            hashMapOf(
                "name" to Recipes.recipe_name,
                "ingredients" to JSONArray(Recipes.ingredients).toString(),
                "steps" to jArr.toString()
            )
        )

        val storage = Firebase.storage
        var storageRef = storage.reference
        var file = Recipes.preview_image

        var uploadTask = storageRef.child("${Recipes.recipe_name}/mainImage/ImgFile").putFile(file!!)

        for (i in 0 until Recipes.steps_array.size){
            if (Recipes.steps_array[i].image != null){
                file = Recipes.steps_array[i].image
                uploadTask = storageRef.child("${Recipes.recipe_name}/steps/step${i}/ImgFile").putFile(file!!)
            }
        }

        uploadTask.addOnSuccessListener {

        }
    }
}