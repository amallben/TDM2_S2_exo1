package com.example.dictionnaire


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_data.view.*


class MainAdapter(val activity : MainActivity): RecyclerView.Adapter<CustomViewHolder>() {



    // numberOfItems
    override fun getItemCount(): Int {
        return MainActivity.fruitNames.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.row_data, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder?.view?.fruits.text=MainActivity.fruitNames[position]
        holder?.view?.images.setImageResource(MainActivity.fruitImages[position])


        holder?.view.setOnClickListener{
            val intent = Intent(activity, activity_listdata::class.java)
            intent.putExtra("title", MainActivity.fruitNames[position])
            intent.putExtra("image", MainActivity.fruitImages[position])
            activity.startActivity(intent)
        }


    }



}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}

