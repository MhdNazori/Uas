package com.informatika19100008.MhdNazori_rangking_sekolah

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika19100008.MhdNazori_rangking_sekolah.adapter.ListContent
import com.informatika19100008.MhdNazori_rangking_sekolah.model.rangkingsekolah
import com.informatika19100008.MhdNazori_rangking_sekolah.model.responrangkingsekolah
import com.informatika19100008.MhdNazori_rangking_sekolah.network.koneksi
import kotlinx.android.synthetic.main.activity_insert_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val etnamasiswa = etnama_siswa.text
            val etkelas = et_kelas.text
            val etrangking= et_rangking.text

            if (etnamasiswa.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Jumlah Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (etnamasiswa.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Nama Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                actionData(etnamasiswa.toString(), etnamasiswa.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }
        getData()
    }

    fun formClear() {
        et_nama_orang.text.clear()
        et_kelas.text.clear()
        et_rangking.text.clear()

    }

    fun actionData(namaBarang: String, jmlBarang: String) {
        koneksi.service.insertnama(namaorangg, kelas, rangking)
            .enqueue(object : Callback<responrangkingsekolah> {
                override fun onFailure(call: Call<responrangkingsekolah>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<responrangkingsekolah>,
                    response: Response<responrangkingsekolah>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getnama().enqueue(object : Callback<rangkingsekolah> {
            override fun onFailure(call: Call<rangkingsekolah>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<rangkingsekolah>,
                response: Response<rangkingsekolah>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity, "InsertDataActivity")

                    rv_data_barang.apply {
                        adapter = rvAdapter
                        var layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }
}

