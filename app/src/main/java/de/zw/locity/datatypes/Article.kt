package de.zw.locity.datatypes

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(var headline : String,var articletext : String, var Id : String, var picture : Bitmap) : Parcelable
