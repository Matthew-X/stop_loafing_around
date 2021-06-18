package com.example.stop_loafing_around.ui.slideshow

import android.app.Activity
import android.content.Intent
import android.database.StaleDataException
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.*
import com.example.stop_loafing_around.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {

    private val PICK_PREVIEW_IMG: Int = 2
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

        binding.previewImg.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            (context as Activity).startActivityForResult(gallery, PICK_PREVIEW_IMG)
        }

        ingredients_recycler!!.layoutManager = LinearLayoutManager(context)
        steps_recycler!!.layoutManager = LinearLayoutManager(context)

        return root
    }

    fun processImages(){
        val root: View = binding.root
        steps_recycler = root.findViewById(R.id.steps_recycler)
        for (i in 0 until Recipes.steps_array.size){
            if (Recipes.steps_array[i].image != null) {
                val inputStream = activity?.contentResolver?.openInputStream(Recipes.steps_array[i].image!!)
                val card_image = Drawable.createFromStream(inputStream,Recipes.steps_array[i].image.toString())
                steps_recycler?.findViewById<ImageView>(R.id.step_img)?.setImageDrawable(card_image)
            }
        }
        if (Recipes.preview_image != null){
            val inputStream = activity?.contentResolver?.openInputStream(Recipes.preview_image!!)
            val card_image = Drawable.createFromStream(inputStream,Recipes.preview_image.toString())
            binding.root.findViewById<ImageView>(R.id.preview_img)?.setImageDrawable(card_image)
        }
        steps_recycler = root.findViewById(R.id.steps_recycler)
        steps_recycler?.adapter = s_adapter
        steps_recycler!!.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}