package de.zw.locity.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(var headline : String,var articletext : String, var Id : String, var pictNumb : Int): Parcelable
