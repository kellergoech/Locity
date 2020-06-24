package de.zw.locity.datatypes

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Article(var headline : String,var articletext : String, var Id : String, var picture : Bitmap) : Parcelable
