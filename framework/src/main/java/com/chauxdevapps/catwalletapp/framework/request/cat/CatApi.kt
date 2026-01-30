package com.chauxdevapps.catwalletapp.framework.request.cat

import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET(ApiConstants.IMAGES_SEARCH)
    suspend fun getCats(
        @Query("limit") limit: Int = ApiConstants.LIMIT_VAL,
        @Query("x-api-key") apiKey: String = ApiConstants.API_KEY
    ): List<CatDto>
}
