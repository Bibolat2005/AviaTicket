package com.example.aviatickets.model.network
import com.example.aviatickets.model.entity.Offer
import retrofit2.Call
import retrofit2.http.GET
interface PersonService {
    @GET("person_list")
    fun fetchPersonList(): Call<List<Offer>>
}