package com.braula.abaxapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.braula.abaxapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BeerListFragment.OnBeerSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container?.let {
            savedInstanceState?.let {
                return
            }

            showFragment(BeerListFragment.newInstance())
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is BeerListFragment) {
            fragment.setOnBeerSelectedListener(this)
        }
    }

    override fun onBeerSelected(position: Int) {
        showFragment(BeerDetailsFragment.newInstance(position))
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount >= 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
