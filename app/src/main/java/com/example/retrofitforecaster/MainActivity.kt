package com.example.retrofitforecaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим RecyclerView в макете и настраиваем его
        recyclerView = findViewById(R.id.r_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Создаем экземпляр адаптера и устанавливаем его для RecyclerView
        adapter = WeatherAdapter()
        recyclerView.adapter = adapter

        // Используем корутины для выполнения сетевого запроса в фоновом потоке
        CoroutineScope(Dispatchers.IO).launch {
            val weatherData = RetrofitService.create().getWeatherForecast("Shklov", "0d176793661eab1d39a71fd2994f9bec", "metric")
            // Переключаемся на главный поток для обновления пользовательского интерфейса
            withContext(Dispatchers.Main) {
                adapter.submitList(weatherData.list)
            }
        }
    }
}