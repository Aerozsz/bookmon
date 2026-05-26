package com.worldclock.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.worldclock.app.data.City
import com.worldclock.app.data.CityRepository
import com.worldclock.app.databinding.ActivityMainBinding
import com.worldclock.app.ui.ClockAdapter
import com.worldclock.app.widget.WorldClockWidget

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repo: CityRepository
    private lateinit var adapter: ClockAdapter

    private val ticker = object : Runnable {
        override fun run() {
            adapter.tick()
            handler.postDelayed(this, 1000L)
        }
    }
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        repo = CityRepository(this)
        adapter = ClockAdapter { city -> repo.removeCity(city.id) }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Swipe-to-delete
        val swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder) = false
            override fun onSwiped(vh: RecyclerView.ViewHolder, dir: Int) {
                val pos = vh.adapterPosition
                val removed = adapter.removeAt(pos)
                repo.removeCity(removed.id)
                WorldClockWidget.requestUpdate(this@MainActivity)
                Snackbar.make(binding.root, "${removed.name} removed", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        repo.addCity(removed.id)
                        loadCities()
                        WorldClockWidget.requestUpdate(this@MainActivity)
                    }.show()
            }
        }
        ItemTouchHelper(swipe).attachToRecyclerView(binding.recyclerView)

        binding.fab.setOnClickListener {
            startActivityForResult(Intent(this, AddCityActivity::class.java), REQ_ADD)
        }

        loadCities()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_ADD && resultCode == RESULT_OK) {
            loadCities()
            WorldClockWidget.requestUpdate(this)
        }
    }

    private fun loadCities() {
        adapter.submitList(repo.getSavedCities())
    }

    override fun onResume() {
        super.onResume()
        loadCities()
        handler.post(ticker)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(ticker)
    }

    companion object {
        private const val REQ_ADD = 1001
    }
}
