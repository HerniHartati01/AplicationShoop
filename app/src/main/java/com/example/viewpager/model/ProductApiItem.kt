package com.example.viewpager.model

import android.os.Parcel
import android.os.Parcelable

data class ProductApiItem(
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val id : String,
    val title: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!

    ){
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(price)
        parcel.writeString(id)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductApiItem> {
        override fun createFromParcel(parcel: Parcel): ProductApiItem {
            return ProductApiItem(parcel)
        }

        override fun newArray(size: Int): Array<ProductApiItem?> {
            return arrayOfNulls(size)
        }
    }
}