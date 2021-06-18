package com.example.stop_loafing_around

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.stop_loafing_around.databinding.ActivityMainBinding
import com.example.stop_loafing_around.ui.slideshow.CreateFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_recomended, R.id.nav_search, R.id.nav_create), drawerLayout)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if (resultCode === RESULT_OK){
                val selectedImage:Uri? = data?.data
                for (i in 0 until Recipes.steps_array.size){
                    if (Recipes.steps_array[i].load_img == true) {
                        Recipes.steps_array[i].image = selectedImage
                        Recipes.steps_array[i].load_img = false
                    }
                }
                val NavHostFragment: NavHostFragment? =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
                with(NavHostFragment!!.childFragmentManager.fragments[0]){
                    (this as CreateFragment).processImages()
                }
            }
            2 -> if (resultCode === RESULT_OK){
                val selectedImage:Uri? = data?.data
                Recipes.preview_image = selectedImage
                val NavHostFragment: NavHostFragment? =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
                with(NavHostFragment!!.childFragmentManager.fragments[0]){
                    (this as CreateFragment).processImages()
                }
            }
        }
    }
}