package com.yj.countries_yan.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yj.countries_yan.R
import com.yj.countries_yan.models.Country
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivityAdapter(
    var countryList: List<Country>,
    private val favBtnClickedHandler: (Int) -> Unit,
    ) : RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder>() {

    inner class MainActivityViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
        init {
            itemView.findViewById<Button>(R.id.btn_favorite).setOnClickListener {
                favBtnClickedHandler(adapterPosition)
            }
        }
    }

    // tell the function which layout file to use for each row of the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.main_activity_adapter, parent, false)
        return MainActivityViewHolder(view)
    }

    // how many items are in the list
    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_country_name).text = "${countryList[position].name.common}"
    }

}
