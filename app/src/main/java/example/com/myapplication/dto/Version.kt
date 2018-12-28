package example.com.myapplication.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Version(
        @SerializedName("msg") val msg: String
) : Parcelable