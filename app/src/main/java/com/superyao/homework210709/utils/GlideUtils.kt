package com.superyao.homework210709.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

fun ImageView.roundedCornersThumbnail(url: String?) {
    Glide.with(context)
        .load(url)
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