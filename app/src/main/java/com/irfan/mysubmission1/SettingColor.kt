package com.irfan.mysubmission1

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun ImageView.changeIconColor(@ColorRes color: Int) {
    setColorFilter(ContextCompat.getColor(context, color), android.graphics.PorterDuff.Mode.SRC_IN)
}
