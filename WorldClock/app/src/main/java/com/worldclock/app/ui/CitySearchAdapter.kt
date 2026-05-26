package com.worldclock.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.worldclock.app.data.City
import com.worldclock.app.databinding.ItemCitySearchBinding
import java.util.TimeZone
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

class CitySearchAdapter(
    private val onAdd: (City) -> Unit
) : RecyclerView.Adapter<CitySearchAdapter.ViewHolder>() {

    private val items = mutableListOf<City>()
    private val savedIds = mutableSetOf<String>()

    fun submitList(cities: List<City>) {
        items.clear()
        items.addAll(cities)
        notifyDataSetChanged()
    }

    fun setSavedIds(ids: Set<String>) {
        savedIds.clear()
        savedIds.addAll(ids)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemCitySearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val b: ItemCitySearchBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(city: City) {
            b.textCityName.text = "${city.flag}  ${city.name}"
            b.textCountry.text = city.country

            val tz = TimeZone.getTimeZone(city.timezone)
            val cal = Calendar.getInstance(tz)
            val fmt = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            fmt.timeZone = tz
            b.textCurrentTime.text = fmt.format(cal.time)

            val alreadyAdded = savedIds.contains(city.id)
            b.btnAdd.isEnabled = !alreadyAdded
            b.btnAdd.text = if (alreadyAdded) "Added" else "Add"
            b.btnAdd.alpha = if (alreadyAdded) 0.4f else 1f

            b.btnAdd.setOnClickListener {
                if (!alreadyAdded) {
                    onAdd(city)
                    savedIds.add(city.id)
                    notifyItemChanged(adapterPosition)
                }
            }

            b.root.setOnClickListener {
                if (!alreadyAdded) {
                    onAdd(city)
                    savedIds.add(city.id)
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }
}
