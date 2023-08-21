package com.example.viewpager.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val image: String,
    val description: String,
    val price: String,
    val title: String
 ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeString(price)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Favorite> {
        override fun createFromParcel(parcel: Parcel): Favorite {
            return Favorite(parcel)
        }

        override fun newArray(size: Int): Array<Favorite?> {
            return arrayOfNulls(size)
        }
    }
}


