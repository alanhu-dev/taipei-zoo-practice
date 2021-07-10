package com.superyao.taipeizoo.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.superyao.taipeizoo.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

fun ImageView.roundedCornersThumbnail(url: String?, @DrawableRes placeholder: Int) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .error(R.drawable.error)
        .apply(
            RequestOptions().transform(
                CenterCrop(),
                RoundedCornersTransformation(
                    20,
                    0,
                    RoundedCornersTransformation.CornerType.ALL
                )
            )
        )
        .into(this)
}