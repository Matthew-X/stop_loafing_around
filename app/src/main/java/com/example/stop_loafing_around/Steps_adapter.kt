package com.example.stop_loafing_around

import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doBeforeTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.databinding.FragmentCreateBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Steps_adapter(private val adapter_array: ArrayList<One_step> = Recipes.steps_array) :
    RecyclerView.Adapter<Steps_adapter.ViewHolder>() {

    private var _binding: FragmentCreateBinding? = null
    private val binding = _binding!!

    class ViewHolder(view: View ) : RecyclerView.ViewHolder(view){
        val step_description: TextInputEditText
        val step_timer_hours: TextInputEditText
        val step_timer_minutes: TextInputEditText
        val step_timer_seconds: TextInputEditText
        val step_hint: TextInputLayout
        init {
            step_description = view.findViewById(R.id.ingredient_text)
            step_timer_hours = view.findViewById(R.id.ingredient_text)
            step_timer_minutes = view.findViewById(R.id.ingredient_text)
            step_timer_seconds = view.findViewById(R.id.ingredient_text)
            step_hint = view.findViewById(R.id.ingredint_hint)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Steps_adapter.ViewHolder, position: Int) {
        holder.step_hint.hint = "Step " + (position+1)
        holder.step_description.text = SpannableStringBuilder(adapter_array[position].description)
        holder.step_description.doBeforeTextChanged { text, start, count, after ->
            adapter_array[position].description = text as Editable
        }
        holder.step_timer_hours.doBeforeTextChanged { text, start, count, after ->
            adapter_array[position].timer[0] = text.toString().toInt()
        }
        holder.step_timer_minutes.doBeforeTextChanged { text, start, count, after ->
            adapter_array[position].timer[1] = text.toString().toInt()
        }
        holder.step_timer_seconds.doBeforeTextChanged { text, start, count, after ->
            adapter_array[position].timer[2] = text.toString().toInt()
        }
    }

    override fun getItemCount(): Int {
        return adapter_array.size
    }

}