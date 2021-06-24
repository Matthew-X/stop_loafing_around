package com.example.stop_loafing_around.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.provider.AlarmClock
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.One_step
import com.example.stop_loafing_around.R
import com.example.stop_loafing_around.Recipe_to_load
import com.example.stop_loafing_around.Recipes
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Show_steps_adapter (val adapter_array: ArrayList<One_step> = Recipe_to_load.steps_array,val activity: Activity? = null) :
    RecyclerView.Adapter<Show_steps_adapter.ViewHolder>() {

    var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val step_description: TextInputEditText
        val step_timer_hours: TextInputEditText
        val step_timer_minutes: TextInputEditText
        val step_timer_seconds: TextInputEditText
        val start_timer_b: LinearLayout
        val step_hint: TextInputLayout
        val step_img: ImageView
        val step_card_img: CardView
        init {
            step_description = view.findViewById(R.id.step_text)
            step_timer_hours = view.findViewById(R.id.hours_step)
            step_timer_minutes = view.findViewById(R.id.minutes_step)
            step_timer_seconds = view.findViewById(R.id.seconds_step)
            step_hint = view.findViewById(R.id.step_number_hint)
            step_img = view.findViewById(R.id.step_img)
            step_card_img = view.findViewById(R.id.show_card_img)
            start_timer_b = view.findViewById(R.id.linearLayout_show_timer)
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
            .inflate(R.layout.show_step_layout,parent,false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.step_hint.hint = "Step " + (position+1)
        holder.step_description.text = adapter_array[position].description
        if (adapter_array[position].image != null){
            holder.step_card_img.visibility = View.VISIBLE
            val inputStream = context?.contentResolver?.openInputStream(adapter_array[position].image!!)
            var step_image = Drawable.createFromStream(inputStream,adapter_array[position].image.toString())
            holder.step_img.setImageDrawable(step_image)
        }else {
            holder.step_card_img.visibility = View.GONE
        }
        if (
            adapter_array[position].timer[0].toString().toInt() == 0 &&
            adapter_array[position].timer[1].toString().toInt() == 0 &&
            adapter_array[position].timer[2].toString().toInt() == 0
        ){
            holder.start_timer_b.visibility = View.GONE
        }else{
        holder.step_timer_hours.text = adapter_array[position].timer[0]
        holder.step_timer_minutes.text = adapter_array[position].timer[1]
        holder.step_timer_seconds.text = adapter_array[position].timer[2]
        }
        holder.start_timer_b.setOnClickListener{
            val intent = Intent(AlarmClock.ACTION_SET_TIMER)
            intent.putExtra(AlarmClock.EXTRA_LENGTH, (Recipes.steps_array[position].timer[0].toString().toInt()*60*60+Recipes.steps_array[position].timer[1].toString().toInt()*60+Recipes.steps_array[position].timer[2].toString().toInt()))
            activity?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return adapter_array.size
    }

    fun update(){
        this.notifyDataSetChanged()
    }
}