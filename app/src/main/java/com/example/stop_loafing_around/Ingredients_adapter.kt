package com.example.stop_loafing_around

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Ingredients_adapter(val adapter_array: ArrayList<String> = Recipes.ingredients) :
    RecyclerView.Adapter<Ingredients_adapter.ViewHolder>() {

    class ViewHolder(view: View ) : RecyclerView.ViewHolder(view){
        val ingredient: TextInputEditText
        val ingredient_hint: TextInputLayout
        init {
            ingredient = view.findViewById(R.id.ingredient_text)
            ingredient_hint = view.findViewById(R.id.ingredint_hint)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Ingredients_adapter.ViewHolder, position: Int) {
        holder.ingredient_hint.hint = "Ingredient " + (position+1)
        holder.ingredient.text = SpannableStringBuilder(adapter_array[position])
        holder.ingredient.doOnTextChanged { text, start, count, after ->
            adapter_array[position] = text.toString()
        }
    }

    override fun getItemCount(): Int {
        return adapter_array.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}