package com.example.stop_loafing_around.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.LoadRecipe
import com.example.stop_loafing_around.Recipe_to_load
import com.example.stop_loafing_around.adapters.Display_recomended_adapter
import com.example.stop_loafing_around.databinding.FragmentRecomendedBinding
import com.example.stop_loafing_around.trimCache
import java.io.File

class RecomendedFragment : Fragment() {

    private lateinit var recomendedViewModel: RecomendedViewModel
    private var _binding: FragmentRecomendedBinding? = null
    var recomended_recycler: RecyclerView? = null
    val r_adapter = Display_recomended_adapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecomendedBinding.inflate(inflater, container, false)
        recomended_recycler = binding.previewRecomendedRecycler
        recomended_recycler?.adapter = r_adapter
        recomended_recycler!!.layoutManager = LinearLayoutManager(context)
        trimCache(requireContext())
        Recipe_to_load.resetRecipeALL()
        LoadRecipe().getRecipePreview(recomended_recycler,requireContext())
        return _binding?.root
    }
}