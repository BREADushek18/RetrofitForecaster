package com.example.retrofitforecaster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

// Адаптер для RecyclerView, использующий ListAdapter для автоматического обновления списка
class WeatherAdapter : ListAdapter<WeatherForecast, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        // Объект DiffUtil.ItemCallback для сравнения элементов списка
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherForecast>() {
            override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
                return oldItem.dt_txt == newItem.dt_txt
            }

            override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
                return oldItem == newItem
            }
        }
    }

    // В методе getItemViewType определяется тип погоды (солнечно или снежно)
    override fun getItemViewType(position: Int): Int {
        val temp = getItem(position).main.temp
        return if (temp >= 0) {
            R.layout.hot_layout // Макет для солнечной погоды
        } else {
            R.layout.cold_layout // Макет для снежной погоды
        }
    }


    // В методе onCreateViewHolder создается ViewHolder для каждого типа погоды
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.hot_layout -> ViewHolderHot(view) // ViewHolder для солнечной погоды
            R.layout.cold_layout -> ViewHolderCold(view) // ViewHolder для снежной погоды
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    // В методе onBindViewHolder устанавливается изображение в зависимости от типа погоды
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val weather = getItem(position)
        when (holder) {
            is ViewHolderHot -> holder.bind(weather) // Привязка данных для солнечной погоды
            is ViewHolderCold -> holder.bind(weather) // Привязка данных для снежной погоды
        }
    }
}




