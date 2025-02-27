package otus.homework.coroutines.di

import otus.homework.coroutines.data.FactsRepositoryImpl
import otus.homework.coroutines.data.PictureRepositoryImpl
import otus.homework.coroutines.data.services.CatsService
import otus.homework.coroutines.presentation.FactsRepository
import otus.homework.coroutines.presentation.PictureRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiContainer {
    private val catsService by lazy {
        retrofit.create(CatsService::class.java)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(FACT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val pictureRepository : PictureRepository by lazy {
        PictureRepositoryImpl(catsService)
    }

    val factsRepository : FactsRepository by lazy {
        FactsRepositoryImpl(catsService)
    }
    companion object{
        private const val FACT_URL = "https://catfact.ninja/"
    }
}