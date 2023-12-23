package com.yj.countries_yan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yj.countries_yan.R
import com.yj.countries_yan.models.Country

class FavListAdapter(
    var favList: List<Country>,
    private val rowClickedHandler: (Int) -> Unit,
) : RecyclerView.Adapter<FavListAdapter.FavListViewHolder>() {

    inner class FavListViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
        init {
            itemView.setOnClickListener {
                rowClickedHandler(adapterPosition)
            }
        }
    }

    // tell the function which layout file to use for each row of the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.fav_list_adapter, parent, false)
        return FavListViewHolder(view)
    }

    // how many items are in the list
    override fun getItemCount(): Int {
        return favList.size
    }

    override fun onBindViewHolder(holder: FavListViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_country_name).text = "${favList[position].name.common}"
    }

}