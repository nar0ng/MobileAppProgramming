package com.example.my11aplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentHostCallback
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.my11aplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    class myFragmentAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init{
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        override fun getItemCount(): Int {
            return fragments.size
        }
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.viewpager2.adapter = myFragmentAdapter(this)//아답터 연결
    }

    // ? -> null을 허용하는 변수이다
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar, menu)

        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("mobileApp", "검색어: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
                return true
            }

        } )
        return super.onCreateOptionsMenu(menu)
    }

    //menu1이 클리됐을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> {
                Log.d("mobileApp", "Menu1")
            }
            R.id.menu2 -> {

            }
            R.id.menu_search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}