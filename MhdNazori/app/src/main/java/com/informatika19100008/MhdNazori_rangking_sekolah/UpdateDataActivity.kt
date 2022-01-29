package com.informatika19100008.MhdNazori_rangking_sekolah

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika19100008.MhdNazori_rangking_sekolah.adapter.ListContent
import com.informatika19100008.MhdNazori_rangking_sekolah.model.responrangkingsekolah
import com.informatika19100008.MhdNazori_rangking_sekolah.model.responuser
import com.informatika19100008.MhdNazori_rangking_sekolah.network.koneksi
import com.informatika19100018.databarang.adapter.ListContent
import com.informatika19100018.databarang.model.ResponseActionBarang
import com.informatika19100018.databarang.model.ResponseBarang
import com.informatika19100018.databarang.network.koneksi
import kotlinx.android.synthetic.main.activity_update_data.*
import kotlinx.android.synthetic.main.activity_update_data.et_jumlah_barang
import kotlinx.android.synthetic.main.activity_update_data.et_nama_barang
import kotlinx.android.synthetic.main.activity_update_data.rv_data_barang
import kotlinx.android.synthetic.main.activity_update_data.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        toolbar.title = "UPDATE DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        val i = intent
        val id = i.getStringExtra("IDBARANG")
        val nama_siswa = i.getStringExtra("nama_siswa")
        val kelas = i.getStringExtra("kelas")
        val rangking = i.getStringExtra("rangking")

        et_nama_siswa.setText(namasiswa)
        et_kelas.setText(kelas)
        et_rangking.setText(rangking)

        btn_submit.setOnClickListener {
            val etnama_siswa = et_nama_siswa.text
            val etkelas = et_kelas.text
            val etrangking = et_rangking.text

            if (etnama_siswa.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else if (etkelas.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Kelas Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
                }
            }else{
                actionData(nama_siswa.toString(), kelas.toString(), rangking.toString())
            }
        }
        btn_back.setOnClickListener {
            finish()
        }
        getData()
    }
    fun actionData(id : String, namaBarang : String, jmlBarang : String){
        koneksi.service.updateBarang(id, namaBarang, jmlBarang).enqueue(object : Callback<responrangkingsekolah>{
            override fun onFailure(call: Call<responrangkingsekolah>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<responrangkingsekolah>,
                response: Response<responrangkingsekolah>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@UpdateDataActivity, "data berhasil diupdate", Toast.LENGTH_LONG).show()
                    getData()
                }
            }
        })
    }
    fun getData(){
        koneksi.service.getnama().enqueue(object : Callback<responuser>{
            override fun onFailure(call: Call<responuser>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<responuser>,
                response: Response<responuser>
            ) {
                if (response.isSuccessful){
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@UpdateDataActivity, "UpdateDataActivity")


                    rv_data_barang.apply {
                        var adapter = rvAdapter
                        var layoutManager = LinearLayoutManager(this@UpdateDataActivity)
                    }

                    }
                }

        })
    }
}