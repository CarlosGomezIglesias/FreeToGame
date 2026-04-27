package com.programa1.freetogame.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.programa1.freetogame.R
import com.programa1.freetogame.adapters.ScreenshotAdapter
import com.programa1.freetogame.data.Game
import com.programa1.freetogame.data.GameService
import com.programa1.freetogame.data.Screenshots
import com.programa1.freetogame.databinding.ActivityDetailBinding
import com.programa1.freetogame.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var game: Game
    lateinit var developer: GameService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra("GAME_ID", -1)

        CoroutineScope(Dispatchers.IO).launch {
            game = GameService.getInstance().getGameById(id)

            CoroutineScope(Dispatchers.Main).launch {
                loadData()
            }
        }
        binding.showMoreButton.setOnClickListener {
            if (binding.descriptionDetail.maxLines == 5) {
                binding.showMoreButton.text = "Show less"
                binding.descriptionDetail.maxLines = Int.MAX_VALUE

            } else {
                binding.descriptionDetail.maxLines = 5
                binding.showMoreButton.text = "Show more"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.menu_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Mira que juego esta disponible gratis:\n ${game.profileUrl}"
                )
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }

        }
    }

    fun loadData() {
        supportActionBar?.title = game.title

        if (game.platform == "Windows") {
            binding.platform.setImageResource(R.drawable.ic_windows)
        } else {
            binding.platform.setImageResource(R.drawable.ic_internet)
        }
        binding.genreText.text = game.genre
        binding.titleDetail.text = game.title
        binding.descriptionDetail.text = game.description
        binding.developerText.text = game.developer
        binding.releaseDateText.text = game.releaseDate

        game.screenshots?.let {
            val adapter = ScreenshotAdapter(it)
            binding.screenshotsRecyclerView.adapter = adapter
        }


        Picasso.get().load(game.image).into(binding.thumbnailDetailImageView)
        binding.playButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(game.gameUrl.toUri())
            startActivity(intent)
        }
    }
}