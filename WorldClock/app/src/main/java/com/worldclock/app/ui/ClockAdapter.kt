package com.worldclock.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.worldclock.app.R
import com.worldclock.app.data.City
import com.worldclock.app.databinding.ItemClockBinding
import java.text.SimpleDateFormat
import java.util.*

class ClockAdapter(
    private val onRemove: (City) -> Unit
) : RecyclerView.Adapter<ClockAdapter.ViewHolder>() {

    private val items = mutableListOf<City>()
    private val activeHolders = mutableSetOf<ViewHolder>()

    fun submitList(cities: List<City>) {
        items.clear()
        items.addAll(cities)
        notifyDataSetChanged()
    }

    fun removeAt(position: Int): City {
        val city = items.removeAt(position)
        notifyItemRemoved(position)
        return city
    }

    fun tick() = activeHolders.forEach { it.updateTime() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemClockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        activeHolders.add(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        activeHolders.remove(holder)
    }

    inner class ViewHolder(private val b: ItemClockBinding) : RecyclerView.ViewHolder(b.root) {

        private lateinit var city: City

        fun bind(c: City) {
            city = c
            b.textCity.text = "${c.flag}  ${c.name}"
            b.textCountry.text = c.country
            updateTime()
        }

        fun updateTime() {
            if (!::city.isInitialized) return
            val tz = TimeZone.getTimeZone(city.timezone)
            val cal = Calendar.getInstance(tz)
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val minute = cal.get(Calendar.MINUTE)
            val second = cal.get(Calendar.SECOND)

            b.textTime.text = String.format("%02d:%02d", hour, minute)
            b.textSeconds.text = String.format(":%02d", second)

            val dayFmt = SimpleDateFormat("EEE, MMM d", Locale.ENGLISH)
            dayFmt.timeZone = tz
            b.textDate.text = dayFmt.format(cal.time)

            val offsetMs = tz.getOffset(cal.timeInMillis).toLong()
            val offsetHours = offsetMs / 3_600_000
            val offsetMins = Math.abs((offsetMs % 3_600_000) / 60_000)
            val sign = if (offsetHours >= 0) "+" else "-"
            b.textUtcOffset.text = "UTC${sign}${Math.abs(offsetHours)}${if (offsetMins > 0) ":${String.format("%02d", offsetMins)}" else ""}"

            val localOffset = TimeZone.getDefault().getOffset(cal.timeInMillis)
            val diffMs = offsetMs - localOffset
            val diffH = diffMs / 3_600_000
            val diffM = Math.abs((diffMs % 3_600_000) / 60_000)
            b.textRelative.text = when {
                diffMs == 0L -> "Same as you"
                diffM == 0L -> "${if (diffH > 0) "+" else ""}${diffH}h from you"
                else -> "${if (diffH >= 0) "+" else ""}${diffH}h ${diffM}m from you"
            }

            val isDay = hour in 6..19
            if (isDay) {
                b.timeIndicator.setBackgroundColor(itemView.context.getColor(R.color.day_color))
                b.iconDayNight.setImageResource(R.drawable.ic_sun)
                b.iconDayNight.imageTintList = android.content.res.ColorStateList.valueOf(
                    itemView.context.getColor(R.color.day_color))
            } else {
                b.timeIndicator.setBackgroundColor(itemView.context.getColor(R.color.night_color))
                b.iconDayNight.setImageResource(R.drawable.ic_moon)
                b.iconDayNight.imageTintList = android.content.res.ColorStateList.valueOf(
                    itemView.context.getColor(R.color.night_color))
            }
        }
    }
}
