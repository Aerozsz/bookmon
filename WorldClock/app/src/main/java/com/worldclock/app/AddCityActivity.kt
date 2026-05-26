package com.worldclock.app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.worldclock.app.data.City
import com.worldclock.app.data.CityRepository
import com.worldclock.app.databinding.ActivityAddCityBinding
import com.worldclock.app.ui.CitySearchAdapter

class AddCityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCityBinding
    private lateinit var repo: CityRepository
    private lateinit var adapter: CitySearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add City"

        repo = CityRepository(this)

        adapter = CitySearchAdapter { city ->
            repo.addCity(city.id)
            setResult(RESULT_OK)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val savedIds = repo.getSavedCities().map { it.id }.toSet()
        adapter.setSavedIds(savedIds)
        showCities(CityRepository.ALL_CITIES)

        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s?.toString()?.trim()?.lowercase() ?: ""
                val filtered = if (query.isEmpty()) {
                    CityRepository.ALL_CITIES
                } else {
                    CityRepository.ALL_CITIES.filter {
                        it.name.lowercase().contains(query) || it.country.lowercase().contains(query)
                    }
                }
                showCities(filtered)
            }
        })
    }

    private fun showCities(cities: List<City>) {
        adapter.submitList(cities)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
