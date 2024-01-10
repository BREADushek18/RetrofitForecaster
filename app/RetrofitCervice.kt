package com.example.retrofitforecaster

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): WeatherResponse

    companion object {
        fun create(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }
    }
}

data class WeatherResponse(val list: List<WeatherForecast>)
data class WeatherForecast(val dt_txt: String, val main: Main)
data class Main(val temp: Double)

class ViewHolderHot(view: View) : RecyclerView.ViewHolder(view) {
    private val textView: TextView = view.findViewById(R.id.textView)

    fun bind(forecast: WeatherForecast) {
        textView.text = "${forecast.dt_txt}: ${forecast.main.temp} °C"
    }
}

class ViewHolderCold(view: View) : RecyclerView.ViewHolder(view) {
    private val textView: TextView = view.findViewById(R.id.textView)

    fun bind(forecast: WeatherForecast) {
        textView.text = "${forecast.dt_txt}: ${forecast.main.temp} °C"
    }
}