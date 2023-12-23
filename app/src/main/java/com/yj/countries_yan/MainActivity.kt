package com.yj.countries_yan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yj.countries_yan.adapter.MainActivityAdapter
import com.yj.countries_yan.api.MyInterface
import com.yj.countries_yan.api.RetrofitInstance
import com.yj.countries_yan.databinding.ActivityMainBinding
import com.yj.countries_yan.models.Country
import com.yj.countries_yan.models.Name
import com.yj.countries_yan.repositories.CountryRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = this.toString()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainActivityAdapter
    private lateinit var countryList: List<Country>
    private var api = RetrofitInstance.retrofitService

    private lateinit var countryRepository: CountryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up menu
        setSupportActionBar(this.binding.menuToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        lifecycleScope.launch {
            countryList = api.getAllCountries()

            //set up the adapter
            adapter = MainActivityAdapter(countryList,  {pos -> favBtnClicked(pos)})
            binding.rvMainActivity.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvMainActivity.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            binding.rvMainActivity.adapter = adapter
        }

        this.countryRepository = CountryRepository(applicationContext)
    }

    private fun favBtnClicked(pos: Int){
//        val newFavName = Name(common = countryList[pos].name.common)
        val newFav = Country(name = countryList[pos].name)
        Log.d(TAG, "favBtnClicked: newFav: $newFav")

        // add country to favorite list database
        countryRepository.addCountryToDB(newFav)
        Snackbar.make(binding.root, "${countryList[pos].name.common} is added to your favorite list!", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_fav_list -> {
                val intent = Intent(this@MainActivity, FavoriteListActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}