package com.example.project

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class CreditRecord(
    var name: String,
    val category: String,
    val creationTime: Date,
    var lastModifiedTime: Date? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        Date(parcel.readLong()),
        parcel.readSerializable() as? Date
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeLong(creationTime.time)
        parcel.writeSerializable(lastModifiedTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreditRecord> {
        override fun createFromParcel(parcel: Parcel): CreditRecord {
            return CreditRecord(parcel)
        }

        override fun newArray(size: Int): Array<CreditRecord?> {
            return arrayOfNulls(size)
        }
    }
}
