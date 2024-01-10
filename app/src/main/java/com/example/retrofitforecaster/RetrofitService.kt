package com.example.retrofitforecaster

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
// Интерфейс для выполнения сетевых запросов с помощью Retrofit
interface RetrofitService {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): WeatherResponse

    companion object { // Создание экземпляра RetrofitService с помощью Retrofit.Builder
        fun create(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }
    }
}
// Data classes для представления данных о погоде
data class WeatherResponse(val list: List<WeatherForecast>)
data class WeatherForecast(val dt_txt: String, val main: Main)
data class Main(val temp: Double)
// ViewHolder классы для RecyclerView
class ViewHolderHot(view: View) : RecyclerView.ViewHolder(view) {
    // Метод для привязки данных к представлению элемента списка (Hot)
    private val textView: TextView = view.findViewById(R.id.textView)

    fun bind(forecast: WeatherForecast) {
        textView.text = "${forecast.dt_txt}: ${forecast.main.temp} °C"
    }
}

class ViewHolderCold(view: View) : RecyclerView.ViewHolder(view) {
    // Метод для привязки данных к представлению элемента списка (Cold)
    private val textView: TextView = view.findViewById(R.id.textView)

    fun bind(forecast: WeatherForecast) {
        textView.text = "${forecast.dt_txt}: ${forecast.main.temp} °C"
    }
}