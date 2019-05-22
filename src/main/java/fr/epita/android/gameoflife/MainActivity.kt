package fr.epita.android.gameoflife

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    fun changeFragment(grid : Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
// Create a new fragment instance
        val fragment = if (grid) GridFragment() else MainFragment()

        fragmentTransaction.replace(R.id.fragment, fragment)
// Commit transaction
        fragmentTransaction.commit()
    }
}
