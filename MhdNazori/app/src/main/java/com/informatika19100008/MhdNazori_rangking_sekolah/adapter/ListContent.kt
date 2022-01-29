package com.informatika19100008.MhdNazori_rangking_sekolah.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.informatika19100008.MhdNazori_rangking_sekolah.R
import com.informatika19100008.MhdNazori_rangking_sekolah.model.DataItem
import com.informatika19100008.databorang.InsertDataActivity
import com.informatika19100008.dataorang.MainActivity
import com.informatika19100008.dataorang.R
import com.informatika19100008.dataorang.UpdateDataActivity
import com.informatika19100008.dataorang.model.DataItem
import com.informatika19100008.dataorang.model.ResponseActionBarang
import com.informatika19100008.dataorang.network.koneksi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListContent(val ldata : List<DataItem?>?, val context: Context, val kondisi : String) :
        RecyclerView.Adapter<ListContent.ViewHolder>() {
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            val namaorang = view.findViewById<TextView>(R.id.tv_nama_barang)
            val kelas = view.findViewById<TextView>(R.id.tv_kelas)
            val rangking = view.findViewById<TextView>(R.id.tv_rangking)
            val editorang = view.findViewById<TextView>(R.id.tv_edit)
            val deleteorang = view.findViewById<TextView>(R.id.tv_delete)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return ldata!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = ldata?.get(position)
        holder.namasiswa.text = model?.namasiswa
        holder.kelas.text = model?.kelas
        holder.editsiswa.setOnClickListener {
            val i = Intent(context, UpdateDataActivity::class.java)
            i.putExtra("IDSISWA", model?.id)
            i.putExtra("NAMASISWA", model?.namasiswa)
            i.putExtra("KELAS", model?.kelas)
            context.startActivity(i)
        }
        holder.deleteBarang.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete" + model?.namaorang)
                .setMessage("Apakah Anda Ingin Mengahapus Data Ini?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    koneksi.service.deleteBarang(model?.id).enqueue(object : Callback<ResponseActionBarang>{
                        override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                            Log.d("pesan1", t.localizedMessage)
                        }

                        override fun onResponse(
                            call: Call<ResponseActionBarang>,
                            response: Response<ResponseActionBarang>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show()
                                notifyDataSetChanged()
                                notifyItemRemoved(position)
                                notifyItemChanged(position)
                                notifyItemRangeChanged(position, ldata!!.size)

                                if(kondisi == " InsertDataActivity"){
                                    val activity = (context as InsertDataActivity)
                                    activity.getData()
                                }else if(kondisi == " UpdateDataActivity"){
                                    val activity = (context as UpdateDataActivity)
                                    activity.getData()
                                }else{
                                    val activity = (context as MainActivity)
                                    activity.getData()

                                }
                                Log.d("bpesan", response.body().toString())

                            }
                        }
                    })
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                .show()
        }
    }


}

class RecyclerView {

}
