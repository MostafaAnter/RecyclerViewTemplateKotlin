package com.recyclerviewtemplate.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.recyclerviewtemplate.R

/**
 * Created by f22labs on 15/06/17.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_simple_list -> startActivity(Intent(this@MainActivity, SimpleListActivity::class.java))
            R.id.btn_simple_list_cards -> startActivity(Intent(this@MainActivity, SimpleListCardsActivity::class.java))
            R.id.btn_simple_grid -> startActivity(Intent(this@MainActivity, SimpleGridActivity::class.java))
            R.id.btn_simple_grid_cards -> startActivity(Intent(this@MainActivity, SimpleGridCardsActivity::class.java))
            R.id.btn_banner_list -> startActivity(Intent(this@MainActivity, BannerListActivity::class.java))
            R.id.btn_banner_grid -> startActivity(Intent(this@MainActivity, BannerGridActivity::class.java))
            R.id.btn_radio_list -> startActivity(Intent(this@MainActivity, RadioListActivity::class.java))
            R.id.btn_radio_grid -> startActivity(Intent(this@MainActivity, RadioGridActivity::class.java))
            R.id.btn_check_list -> startActivity(Intent(this@MainActivity, CheckboxListActivity::class.java))
            R.id.btn_check_grid -> startActivity(Intent(this@MainActivity, CheckboxGridActivity::class.java))
            R.id.btn_toggle_list -> startActivity(Intent(this@MainActivity, ToggleListActivity::class.java))
            R.id.btn_toggle_grid -> startActivity(Intent(this@MainActivity, ToggleGridActivity::class.java))
            R.id.btn_header_list -> startActivity(Intent(this@MainActivity, HeaderListActivity::class.java))
            R.id.btn_header_grid -> startActivity(Intent(this@MainActivity, HeaderGridActivity::class.java))
            R.id.btn_footer_list -> startActivity(Intent(this@MainActivity, FooterListActivity::class.java))
            R.id.btn_footer_grid -> startActivity(Intent(this@MainActivity, FooterGridActivity::class.java))
            R.id.btn_header_footer_list -> startActivity(Intent(this@MainActivity, HeaderFooterListActivity::class.java))
            R.id.btn_header_footer_grid -> startActivity(Intent(this@MainActivity, HeaderFooterGridActivity::class.java))
            R.id.btn_google_play -> startActivity(Intent(this@MainActivity, GooglePlayActivity::class.java))
        }
    }
}