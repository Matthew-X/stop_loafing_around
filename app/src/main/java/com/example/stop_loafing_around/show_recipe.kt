package com.example.stop_loafing_around

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.adapters.Display_recomended_adapter
import com.example.stop_loafing_around.adapters.Show_ingredients_adapter
import com.example.stop_loafing_around.adapters.Show_steps_adapter
import com.example.stop_loafing_around.databinding.FragmentRecomendedBinding
import com.example.stop_loafing_around.databinding.ShowRecipeBinding

class show_recipe : AppCompatActivity() {

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
        LoadRecipe().getRecipeData(Recipe_to_load.recipe_name,steps_recycler?.adapter,ingredients_recycler?.adapter)
        binding.createRecipeName.text = Recipe_to_load.recipe_name.toEditable()
        val view = binding.root
        setContentView(view)
    }
}