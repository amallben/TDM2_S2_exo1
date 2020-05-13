package com.example.dictionnaire



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //var listView: ListView? = null
    private var adapter: Adapter? = null
    companion object {var fruitNames =
        arrayOf("Apple", "Orange", "Kiwi", "Passion", "Banana","Pen")
    var fruitImages = intArrayOf(
        R.drawable.apple,
        R.drawable.oranges,
        R.drawable.kiwi,
        R.drawable.watermelon,
        R.drawable.banana,
        R.drawable.pen
    )}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //listView = findViewById(R.id.listview)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = MainAdapter(this)

    }



    class CustomAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val view1: View = LayoutInflater.from(parent?.getContext()).inflate(R.layout.row_data, null)
            //getting view in row_data
            //getting view in row_data
            val name = view1.findViewById<TextView>(R.id.fruits)
            val image =
                view1.findViewById<ImageView>(R.id.images)

            name.text = fruitNames[position]
            image.setImageResource(fruitImages[position])
            return view1
        }

        override fun getItem(position: Int): Any? {
           return null}

        override fun getItemId(position: Int): Long {
            return 0  }

        override fun getCount(): Int {
            return fruitImages.size
        }

    }
}


