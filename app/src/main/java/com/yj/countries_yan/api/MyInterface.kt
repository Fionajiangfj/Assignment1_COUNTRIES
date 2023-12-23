package com.yj.countries_yan.api

import com.yj.countries_yan.models.Country
import retrofit2.http.GET

interface MyInterface {
    @GET("/v3.1/independent?status=true")
    suspend fun getAllCountries(): List<Country>
}