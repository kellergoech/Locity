package de.zw.locity.datatypes

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter(value = ["setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.run {
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

@BindingAdapter(value = ["Bitmap"])
fun ImageView.bindBitmap(bitmap : Bitmap) {
    this.run {
        this.setImageBitmap(bitmap)
    }
}


