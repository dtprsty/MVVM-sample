package id.dtprsty.mvvm_sample.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.dtprsty.mvvm_sample.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)

        supportActionBar?.elevation = 0f
    }
}
