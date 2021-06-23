package com.example.stop_loafing_around

import android.net.Uri
import android.text.Editable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.adapters.Display_recomended_adapter
import com.example.stop_loafing_around.adapters.Show_steps_adapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.min


class LoadRecipe {
    fun getRecipePreview(recomended_recycler: RecyclerView?) {
        val db = Firebase.firestore
        val docRef = db.collection("recipe")
        docRef.get().addOnSuccessListener { documentSnapshot ->
            for (document in documentSnapshot){
                if (!Recipe_to_load.recipes_list.contains(document.get("name").toString())){
                    Recipe_to_load.recipes_list.add(document.get("name").toString())
                }
            }
            Log.d("recipe", Recipe_to_load.recipes_list.toString())
            recomended_recycler?.adapter?.notifyDataSetChanged()
            recomended_recycler?.adapter?.notifyDataSetChanged()
        }
    }
    fun getRecipeData(recipe_name:String = "",adapter1:RecyclerView.Adapter<RecyclerView.ViewHolder>? = null,adapter2:RecyclerView.Adapter<RecyclerView.ViewHolder>? = null){
        val db = Firebase.firestore
        val docRef = db.collection("recipe").document(recipe_name)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            Recipe_to_load.recipe_name = documentSnapshot.data?.get("name").toString()
            val steps = JSONArray(documentSnapshot.data?.get("steps").toString())
            if (steps != null) {
                for (i in 0 until steps.length()) {
                    var timer = arrayListOf<Editable?>()
                        var hours:Int = JSONObject(steps.getString(i)).getString("timer").toInt()/60/60
                        var minutes:Int = JSONObject(steps.getString(i)).getString("timer").toInt()/60 - hours*60
                        var seconds:Int = JSONObject(steps.getString(i)).getString("timer").toInt() - minutes*60 - hours*60*60
                    Log.d("time",seconds.toString())
                        timer = arrayListOf<Editable?>(
                            Editable.Factory.getInstance().newEditable(hours.toString()),
                            Editable.Factory.getInstance().newEditable(minutes.toString()),
                            Editable.Factory.getInstance().newEditable(seconds.toString())
                        )
                    Recipe_to_load.steps_array.add(
                        One_step(
                            description = JSONObject(steps.getString(i)).getString("step_desc")
                                .toEditable(),
                            timer = timer
                        )
                    )
                }
            }

            val ingredients = JSONArray(documentSnapshot.data?.get("ingredients").toString())

            if (ingredients != null){
                for (i in 0 until ingredients.length()){
                    Recipe_to_load.ingredients.add(ingredients.getString(i))
                }
            }
            Log.d("recipe", Recipe_to_load.ingredients.toString())
            adapter1?.notifyDataSetChanged()
            adapter2?.notifyDataSetChanged()
        }
    }
}
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)