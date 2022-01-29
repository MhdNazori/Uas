package com.informatika19100008.MhdNazori_rangking_sekolah.model

import com.google.gson.annotations.SerializedName

data class rangkingsekolah(

    @field:SerializedName( "pesan")
    val pesan: String? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class DataItem(

    @field:SerializedName("nama_siswa")
    val nama_siswa: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("kelas")
    val kelas: String? =null,

    @field:SerializedName("rangking")
    val rangking: String? =null
)