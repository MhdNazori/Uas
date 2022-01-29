package com.informatika19100008.MhdNazori_rangking_sekolah.network

import com.informatika19100008.MhdNazori_rangking_sekolah.model.ResponseAdmin
import com.informatika19100008.MhdNazori_rangking_sekolah.model.rangkingsekolah
import com.informatika19100008.MhdNazori_rangking_sekolah.model.responrangkingsekolah
import com.informatika19100008.MhdNazori_rangking_sekolah.model.responuser
import com.informatika19100018.databarang.model.ResponseActionBarang
import com.informatika19100018.databarang.model.ResponseAdmin
import com.informatika19100018.databarang.model.ResponseBarang
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getrangking(): Call<rangkingsekolah>

    @FormUrlEncoded
    @POST("create.php")
    fun insertrangking(
        @Field("nama_orang") namaorang: String?,
        @Field("kelas") kelas: String?,
        @Field("rangking") rangking: String?
    ): Call<responrangkingsekolah>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
        @Field("id") id: String?,
        @Field("nama_orang") namaorang: String?,
        @Field("kelas") kelas: String?,
        @Field("rangking") rangking: String?
    ): Call<responrangkingsekolah>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
        @Field("id") id: String?
    ): Call<responrangkingsekolah>

    @FormUrlEncoded
    @POST("login.php")
    fun logIn(
        @Field("Username") username : String?,
        @Field("Password") password : String?
    ):Call<ResponseAdmin>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") Username : String?,
        @Field("password") Password : String?
    ):Call<ResponseAdmin>
}