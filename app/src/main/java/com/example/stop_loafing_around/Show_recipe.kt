package com.example.stop_loafing_around

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.adapters.Show_ingredients_adapter
import com.example.stop_loafing_around.adapters.Show_steps_adapter
import com.example.stop_loafing_around.databinding.ShowRecipeBinding

class Show_recipe : AppCompatActivity() {

    private var steps_recycler: RecyclerView? = null
    private var ingredients_recycler: RecyclerView? = null
    private val s_adapter = Show_steps_adapter()
    private val i_adapter = Show_ingredients_adapter()
    private var _binding: ShowRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_recipe)
        _binding = ShowRecipeBinding.inflate(layoutInflater)
        steps_recycler = binding.stepsRecycler
        steps_recycler?.adapter = s_adapter
        steps_recycler!!.layoutManager = LinearLayoutManager(this)
        ingredients_recycler = binding.ingredientsRecyclerView
        ingredients_recycler?.adapter = i_adapter
        ingredients_recycler!!.layoutManager = LinearLayoutManager(this)
        LoadRecipe().getRecipeData(Recipe_to_load.recipe_name,steps_recycler?.adapter,ingredients_recycler?.adapter,this,this)
        binding.createRecipeName.text = Recipe_to_load.recipe_name.toEditable()
        binding.previewImg.setImageDrawable(Recipe_to_load.preview_image.toDrawable(this))
        val view = binding.root

        setContentView(view)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    fun update(){
        _binding = ShowRecipeBinding.inflate(layoutInflater)
        if (Recipe_to_load.preview_image != null) {
            val inputStream = this.contentResolver?.openInputStream(Recipe_to_load.preview_image!!)
            var step_image =
                Drawable.createFromStream(inputStream, Recipe_to_load.preview_image.toString())
            binding.previewImg.setImageDrawable(step_image)
        }else{
            binding.showCardPreviewImg.visibility = View.GONE
        }
        Log.d("preview",Recipe_to_load.preview_image.toString())
    }
}