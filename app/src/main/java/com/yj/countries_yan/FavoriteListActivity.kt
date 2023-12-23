package com.yj.countries_yan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yj.countries_yan.adapter.FavListAdapter
import com.yj.countries_yan.adapter.MainActivityAdapter
import com.yj.countries_yan.databinding.ActivityFavoriteListBinding
import com.yj.countries_yan.databinding.ActivityMainBinding
import com.yj.countries_yan.models.Country
import com.yj.countries_yan.repositories.CountryRepository

class FavoriteListActivity : AppCompatActivity() {
    private val TAG = this.toString()
    private lateinit var binding: ActivityFavoriteListBinding
    private lateinit var countryRepository: CountryRepository
    private lateinit var adapter: FavListAdapter
    private lateinit var favArrayList: ArrayList<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up adapter
        favArrayList = ArrayList()
        adapter = FavListAdapter(favArrayList, {pos -> rowClicked(pos)})
        binding.rvFavActivity.layoutManager = LinearLayoutManager(this)
        binding.rvFavActivity.addItemDecoration(
            DividerItemDecoration(
                this.applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
        this.binding.rvFavActivity.adapter = adapter

        countryRepository = CountryRepository(applicationContext)
    }

    override fun onResume() {
        super.onResume()

        // get favorite list from DB
        countryRepository.retrieveAllCountries()

        countryRepository.allCountries.observe(this, androidx.lifecycle.Observer { favList ->
            Log.d(TAG, "onResume: favList: $favList")
            if (favList != null){
                favArrayList.clear()
                favArrayList.addAll(favList)
                Log.d(TAG, "onResume: favArrayList: $favArrayList")
                adapter.notifyDataSetChanged()
            }
        })
    }

    fun rowClicked(pos: Int){
        val countryToDelete = favArrayList[pos]
        Log.d(TAG, "rowClicked: countryToDelete: $countryToDelete")

        // remove country from database
        countryRepository.removeCountryFromDB(countryToDelete)
        adapter.notifyDataSetChanged()
        Snackbar.make(binding.root, "${favArrayList[pos].name.common} is removed from your favorite list!", Snackbar.LENGTH_LONG).show()
    }
}