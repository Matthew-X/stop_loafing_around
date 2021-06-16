package com.example.stop_loafing_around.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stop_loafing_around.databinding.FragmentRecomendedBinding

class RecomendedFragment : Fragment() {

    private lateinit var recomendedViewModel: RecomendedViewModel
    private var _binding: FragmentRecomendedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        recomendedViewModel =
                ViewModelProvider(this).get(RecomendedViewModel::class.java)

        _binding = FragmentRecomendedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        recomendedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}