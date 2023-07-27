package it.unibo.demo.progetto

import android.app.Application
import it.unibo.demo.progetto.interfaces.FootApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var footApiService: FootApiService
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        footApiService = retrofit.create(FootApiService::class.java)
    }
}