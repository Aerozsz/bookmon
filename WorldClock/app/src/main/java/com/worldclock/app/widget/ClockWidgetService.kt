package com.worldclock.app.widget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.worldclock.app.R
import com.worldclock.app.data.CityRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class ClockWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        ClockFactory(applicationContext)
}

private class ClockFactory(private val ctx: Context) : RemoteViewsService.RemoteViewsFactory {

    private val repo = CityRepository(ctx)
    private var cities = emptyList<com.worldclock.app.data.City>()

    override fun onCreate() { cities = repo.getSavedCities() }
    override fun onDataSetChanged() { cities = repo.getSavedCities() }
    override fun onDestroy() {}
    override fun getCount() = cities.size
    override fun getViewTypeCount() = 1
    override fun getItemId(pos: Int) = cities[pos].id.hashCode().toLong()
    override fun hasStableIds() = true
    override fun getLoadingView() = null

    override fun getViewAt(position: Int): RemoteViews {
        val city = cities[position]
        val views = RemoteViews(ctx.packageName, R.layout.widget_clock_item)

        val tz = TimeZone.getTimeZone(city.timezone)
        val cal = Calendar.getInstance(tz)
        val timeFmt = SimpleDateFormat("HH:mm", Locale.ENGLISH).apply { timeZone = tz }

        views.setTextViewText(R.id.widget_city_name, "${city.flag} ${city.name}")
        views.setTextViewText(R.id.widget_city_time, timeFmt.format(cal.time))

        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val isDay = hour in 6..19
        val indicatorColor = if (isDay) 0xFFFFD700.toInt() else 0xFF6B8CFF.toInt()
        views.setInt(R.id.widget_dot, "setColorFilter", indicatorColor)

        return views
    }
}
