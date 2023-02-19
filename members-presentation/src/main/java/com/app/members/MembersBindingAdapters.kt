package com.app.members

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("shouldShow")
fun showLoadingProgressForMembers(view: View, isLoading: Boolean) {
    view.apply {
        visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("teamCount")
fun setTeamsCount(view: TextView, count: Int) {
    view.text = count.toString()
}

@BindingAdapter("setImage")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}
