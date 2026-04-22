package com.programa1.freetogame.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.programa1.freetogame.R
import com.programa1.freetogame.adapters.GameAdapter
import com.programa1.freetogame.data.Game
import com.programa1.freetogame.data.GameService
import com.programa1.freetogame.databinding.ActivityDetailBinding
import com.programa1.freetogame.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: GameAdapter
    var gameList: List<Game> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter= GameAdapter(gameList){ position ->
            val game = gameList[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("GAME_ID", game.id)
            startActivity(intent)

        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            gameList = GameService.getInstance().getAllGamesList()
            CoroutineScope(Dispatchers.Main).launch {
                adapter.updateData(gameList)
            }
        }
    }
}