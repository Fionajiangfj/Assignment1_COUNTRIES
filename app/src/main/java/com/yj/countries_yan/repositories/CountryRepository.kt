package com.yj.countries_yan.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.yj.countries_yan.models.Country
import com.yj.countries_yan.models.Name

class CountryRepository(private val context: Context) {
    private val TAG = this.toString()
    private val db = Firebase.firestore
    private val COLLECTION_COUNTRIES = "Countries";
    private val FIELD_NAME = "name";

    var allCountries : MutableLiveData<List<Country>> = MutableLiveData<List<Country>>()

    fun addCountryToDB(newFav: Country){
        try {
            val data: MutableMap<String, Any> = HashMap()

            data[FIELD_NAME] = newFav.name

            db.collection(COLLECTION_COUNTRIES)
                .add(data)
                .addOnSuccessListener { docRef ->
                    Log.d(
                        TAG,
                        "addCountryToDB: Document successfully added with ID : ${docRef.id}"
                    )
                }
                .addOnFailureListener { ex ->
                    Log.e(
                        TAG,
                        "addCountryToDB: Exception occurred while adding a document : $ex",
                    )
                }

        }catch (ex: Exception){
            Log.d(
                TAG,
                "addExpenseToDB: Couldn't perform insert on Country collection due to exception $ex"
            )
        }
    }

    fun retrieveAllCountries(){
        try {
            db.collection(COLLECTION_COUNTRIES)
                .addSnapshotListener(EventListener{ result, error ->
                    if (error != null){
                        Log.e(TAG,
                            "retrieveAllCountries: Listening to Countries collection failed due to error : $error", )
                        return@EventListener
                    }

                    if (result != null){
                        Log.d(TAG, "retrieveAllCountries: Number of documents retrieved : ${result.size()}")
                        val tempList : MutableList<Country> = ArrayList()

                        for (docChanges in result.documentChanges){

                            val currCountry = docChanges.document.toObject(Country::class.java)
                            currCountry.id = docChanges.document.id
                            Log.d(TAG, "retrieveAllCountries: currentDocument : $currCountry")

                            when(docChanges.type){
                                DocumentChange.Type.ADDED -> {
                                    tempList.add(currCountry)
                                }
                                DocumentChange.Type.REMOVED -> {
                                    tempList.remove(currCountry)
                                    retrieveAllCountries()
                                }
                                DocumentChange.Type.MODIFIED -> {}
                            }
                        }
                        Log.d(TAG, "retrieveAllCountries: tempList : $tempList")

                        allCountries.postValue(tempList)

                    }else{
                        Log.d(TAG, "retrieveAllCountries: No data in the result after retrieving.")
                    }
                })
        }
        catch (ex : Exception){
            Log.e(TAG, "retrieveAllCountries: Unable to retrieve all countries : $ex", )
        }
    }

    fun removeCountryFromDB(countryToRemove: Country){
        try {
            Log.d(TAG, "removeCountryFromDB: countryToRemove: $countryToRemove")
            db.collection(COLLECTION_COUNTRIES)
                .document(countryToRemove.id)
                .delete()
                .addOnSuccessListener { docRef ->
                    Log.d(TAG, "removeCountryFromDB: Document deleted successfully : $docRef")
                }
                .addOnFailureListener { ex ->
                    Log.e(TAG, "removeCountryFromDB: Failed to delete document : $ex", )
                }
        }
        catch (ex : Exception){
            Log.e(TAG, "removeCountryFromDB: Unable to delete document due to exception : $ex", )
        }
    }
}