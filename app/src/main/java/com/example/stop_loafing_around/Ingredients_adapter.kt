package com.example.stop_loafing_around

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class Ingredients_adapter(private val adapter_array: ArrayList<String>) :
    RecyclerView.Adapter<Ingredients_adapter.ViewHolder>() {

    class ViewHolder(view: View ) : RecyclerView.ViewHolder(view){
        val ingredient: TextInputEditText

        init {
            ingredient = view.findViewById(R.id.ingredient_text)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Ingredients_adapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Ingredients_adapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        adapter_array.size
    }

}