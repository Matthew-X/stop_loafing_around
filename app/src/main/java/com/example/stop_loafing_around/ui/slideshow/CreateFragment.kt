package com.example.stop_loafing_around.ui.slideshow

import android.database.StaleDataException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.*
import com.example.stop_loafing_around.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {

    private lateinit var createViewModel: CreateViewModel
    private var _binding: FragmentCreateBinding? = null
    private var ingredients_recycler: RecyclerView? = null
    private var steps_recycler: RecyclerView? = null
    private val i_adapter = Ingredients_adapter()
    private val s_adapter = Steps_adapter()
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val root:View = binding.root

        ingredients_recycler = root.findViewById(R.id.ingredients_recycler_view)
        ingredients_recycler?.adapter = i_adapter
        steps_recycler = root.findViewById(R.id.steps_recycler)
        steps_recycler?.adapter = s_adapter
        val ingredient_add_b = binding.addIngredientButton
        val step_add_b = binding.addStepButton

        ingredient_add_b.setOnClickListener{
            Recipes.ingredients.add("")
            ingredients_recycler!!.layoutManager = LinearLayoutManager(context)
        }

        step_add_b.setOnClickListener{
            Recipes.steps_array.add(One_step())
            steps_recycler!!.layoutManager = LinearLayoutManager(context)
        }

        ingredients_recycler!!.layoutManager = LinearLayoutManager(context)
        steps_recycler!!.layoutManager = LinearLayoutManager(context)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}