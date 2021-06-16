package com.example.stop_loafing_around.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.Ingredients_adapter
import com.example.stop_loafing_around.Recipes
import com.example.stop_loafing_around.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {

    private lateinit var createViewModel: CreateViewModel
    private var _binding: FragmentCreateBinding? = null
    private var table: RecyclerView? = null
    private val adapter = Ingredients_adapter()
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root:View = binding.root

        val ingredients_recycler = binding.ingredientsRecycler
        ingredients_recycler.adapter = adapter
        val steps_recycler = binding.stepsRecycler
        val ingredient_add_b = binding.addIngredientButton
        val step_add_b = binding.addStepButton

        ingredient_add_b.setOnClickListener{
            Recipes.ingredients.add("")
            ingredients_recycler.layoutManager = LinearLayoutManager(context)
            System.out.println()
        }
        table = binding.ingredientsRecycler

        ingredients_recycler.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}