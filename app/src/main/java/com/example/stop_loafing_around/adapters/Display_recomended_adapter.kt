package com.example.stop_loafing_around.adapters

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.*
import com.google.android.material.textfield.TextInputEditText

class Display_recomended_adapter(val adapter_array: ArrayList<String> = Recipe_to_load.recipes_list) :
    RecyclerView.Adapter<Display_recomended_adapter.ViewHolder>() {

    var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextInputEditText
        val image: ImageView
        val card: CardView
        val click_card: CardView
        init {
            name = view.findViewById(R.id.preview_recomended_name)
            image = view.findViewById(R.id.preview_recomended_img)
            card = view.findViewById(R.id.preview_recomended_card_img)
            click_card = view.findViewById(R.id.preview_recomended_click_card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.display_recipe, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = adapter_array[position].toEditable()
        if (Recipe_to_load.preview_image != null){
            val inputStream = context?.contentResolver?.openInputStream(Recipe_to_load.preview_image!!)
            var step_image = Drawable.createFromStream(inputStream, Recipe_to_load.preview_image.toString())
            holder.image.setImageDrawable(step_image)
        }else holder.card.visibility = View.GONE
        holder.click_card.setOnClickListener{
            Recipe_to_load.reset()
            Recipe_to_load.recipe_name = adapter_array[position]
            val intent = Intent(context, show_recipe::class.java)
            context?.startActivity(intent)
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