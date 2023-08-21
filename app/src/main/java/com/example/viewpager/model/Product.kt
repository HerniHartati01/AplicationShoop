package com.example.viewpager.model

import android.os.Parcel
import android.os.Parcelable

data class Product (val imgProduct:Int, val nameProduct: String, val priceProduct: String, val imgBtnLike: Int)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()

    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imgProduct)
        parcel.writeString(nameProduct)
        parcel.writeString(priceProduct)
        parcel.writeInt(imgBtnLike)
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }


}