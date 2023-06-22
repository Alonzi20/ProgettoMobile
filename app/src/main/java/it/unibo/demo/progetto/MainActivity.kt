package it.unibo.demo.progetto

import android.os.Bundle
import android.view.Menu
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import it.unibo.demo.progetto.databinding.ActivityMainBinding
import it.unibo.demo.progetto.ui.serie_a.SerieAFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // Sezione che gestisce cosa fa il bottone
        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        drawerLayout = binding.drawerLayout
        navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_serie_a, R.id.nav_nba
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /*private fun setupNavigationView() {
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_serie_a -> {
                    // Sostituisci il frammento corrente con il frammento per l'opzione 1
                    val fragment = GiornataSerieA()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.sub_fragment_container, fragment)
                        .commit()
                }
                R.id.nav_nba -> {
                    // Sostituisci il frammento corrente con il frammento per l'opzione 2
                    val fragment = GiornataNba()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.sub_fragment_container, fragment)
                        .commit()
                }
            }

            // Chiudi il menu a comparsa laterale
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun showDefaultFragment() {
        // Mostra il frammento predefinito quando si avvia l'app
        val fragment = GiornataSerieA()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }*/
}