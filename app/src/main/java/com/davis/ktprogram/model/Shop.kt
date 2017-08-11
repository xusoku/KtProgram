package com.davis.ktprogram.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by davis on 16/5/18.
 */
data class Shop(var addtime: String?,
                var edittime: String?,
                var slock: String?,
                var id: String?,
                var shopname: String?,
                var address: String?,
                var tel: String?,
                var picurl: String?,
                var context: String?,
                var addressid: String?,
                var lng: String?,
                var lat: String?,
                var hotarea: String?,
                var center: String?,
                var polygon: String?) : Parcelable {


    constructor(dest: Parcel) : this(
            addtime = dest.readString(),
            edittime = dest.readString(),
            slock = dest.readString(),
            id = dest.readString(),
            shopname = dest.readString(),
            address = dest.readString(),
            tel = dest.readString(),
            picurl = dest.readString(),
            context = dest.readString(),
            addressid = dest.readString(),
            lng = dest.readString(),
            lat = dest.readString(),
            hotarea = dest.readString(),
            center = dest.readString(),
            polygon = dest.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.addtime)
        dest.writeString(edittime)
        dest.writeString(slock)
        dest.writeString(id)
        dest.writeString(shopname)
        dest.writeString(address)
        dest.writeString(tel)
        dest.writeString(picurl)
        dest.writeString(context)
        dest.writeString(addressid)
        dest.writeString(lng)
        dest.writeString(lat)
        dest.writeString(hotarea)
        dest.writeString(center)
        dest.writeString(polygon)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Shop> = object : Parcelable.Creator<Shop> {
            override fun createFromParcel(parcel: Parcel): Shop {
                return Shop(parcel)
            }

            override fun newArray(size: Int): Array<Shop?> {
                return arrayOfNulls(size)
            }
        }
    }
}