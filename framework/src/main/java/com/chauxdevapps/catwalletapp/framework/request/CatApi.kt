package com.chauxdevapps.catwalletapp.framework.request

import com.chauxdevapps.catwalletapp.domain.model.Cat
import retrofit2.http.GET

interface CatApi {
    @GET("v1/images/search?limit=10")
    suspend fun getCats(): List<Cat>
}
