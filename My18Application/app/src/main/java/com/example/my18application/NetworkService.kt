package com.example.my18application

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {
    // http://apis.data.go.kr/B553748/CertImgListService/getCertImgListService
    // getCertImgListService?
    @GET("getCertImgListService")
    //
    fun getList(
        @Query("prdlstNm") q:String,
        @Query("ServiceKey") apikey:String,
        @Query("pageNo") page:Long,
        @Query("numOfRows") pageSize:Int,
        @Query("returnType") returnType:String
    ): Call<MyModel> // 서버로부터 response로 MyModel을 전달 받겠다.
}