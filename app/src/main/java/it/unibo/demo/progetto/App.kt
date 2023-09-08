package it.unibo.demo.progetto

import android.app.Application
import it.unibo.demo.progetto.interfaces.BasketApiService
import it.unibo.demo.progetto.interfaces.FootApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var footApiService: FootApiService
        lateinit var retrofit2: Retrofit
        lateinit var basketApiService: BasketApiService
    }

    override fun onCreate() {
        super.onCreate()

        // Aggiungi questo blocco di codice per configurare l'interceptor per il logging delle richieste e delle risposte
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            // Aggiungi altri interceptor o configurazioni dell'OkHttpClient se necessario
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://footapi7.p.rapidapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient) // Usa l'OkHttpClient con l'interceptor
                .build()

        footApiService = retrofit.create(FootApiService::class.java)

        retrofit2 = Retrofit.Builder()
            .baseUrl("https://basketapi1.p.rapidapi.com/api/basketball/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient) // Usa l'OkHttpClient con l'interceptor
            .build()
        basketApiService = retrofit2.create(BasketApiService::class.java)
    }
}