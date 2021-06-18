package com.example.stop_loafing_around

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.stop_loafing_around.databinding.FragmentCreateBinding
import com.example.stop_loafing_around.ui.slideshow.CreateFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Steps_adapter() :
    RecyclerView.Adapter<Steps_adapter.ViewHolder>() {

    var context: Context? = null
    private val adapter_array: ArrayList<One_step> = Recipes.steps_array
    val pickImage = 1

    class ViewHolder(view: View ) : RecyclerView.ViewHolder(view){
        val step_description: TextInputEditText
        val step_timer_hours: TextInputEditText
        val step_timer_minutes: TextInputEditText
        val step_timer_seconds: TextInputEditText
        val step_hint: TextInputLayout
        val step_img: ImageView
        init {
            step_description = view.findViewById(R.id.step_text)
            step_timer_hours = view.findViewById(R.id.hours_step)
            step_timer_minutes = view.findViewById(R.id.minutes_step)
            step_timer_seconds = view.findViewById(R.id.seconds_step)
            step_hint = view.findViewById(R.id.step_number_hint)
            step_img = view.findViewById(R.id.step_img)
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
            .inflate(R.layout.step_layout,parent,false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Steps_adapter.ViewHolder, position: Int) {
        holder.step_hint.hint = "Step " + (position+1)
        holder.step_description.text = adapter_array[position].description
        if (adapter_array[position].image != null){
            val inputStream = context?.contentResolver?.openInputStream(adapter_array[position].image!!)
            var step_image = Drawable.createFromStream(inputStream,adapter_array[position].image.toString())
            holder.step_img.setImageDrawable(step_image)
        }
        holder.step_description.doOnTextChanged { text, start, count, after ->
            adapter_array[position].description = text as Editable
        }
        holder.step_timer_hours.text = adapter_array[position].timer[0]
        holder.step_timer_hours.doOnTextChanged { text, start, count, after ->
            adapter_array[position].timer[0] = text as Editable
        }
        holder.step_timer_minutes.text = adapter_array[position].timer[1]
        holder.step_timer_minutes.doOnTextChanged { text, start, count, after ->
            adapter_array[position].timer[1] = text as Editable
        }
        holder.step_timer_seconds.text = adapter_array[position].timer[2]
        holder.step_timer_seconds.doOnTextChanged { text, start, count, after ->
            adapter_array[position].timer[2] = text as Editable
        }
        holder.step_img.setOnClickListener{
            adapter_array[position].load_img = true
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            (context as Activity).startActivityForResult(gallery, pickImage)
        }
    }

    override fun getItemCount(): Int {
        return adapter_array.size
    }

}