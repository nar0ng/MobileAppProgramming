package com.example.practicemid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practicemid.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    lateinit var toolbar: ActionBarDrawerToggle
    // 5-(3): MyFragmentAdapter 생성
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        // getItemCount와 createFragment는 반드시 선언해주어야 한다
        //
        // 리스트 타입으로 프레그먼트 선언과 초기화
        val fragments: List<Fragment>

        init {
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
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        binding.viewpager2.adapter = MyFragmentAdapter(this)

        TabLayoutMediator(binding.tabs, binding.viewpager2){
                tab, position -> tab.text = "TAB ${position+1}"
        }.attach()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> {
                Log.d("MobileApp", "내비게이션 메뉴 변경했다")
            }
            R.id.menu2 -> {
                Log.d("MobileApp", "내비게이션 메뉴 변경")
            }
            R.id.menu3 -> {
                Log.d("MobileApp", "내비게이션 메뉴 변경 사항")
            }
            R.id.menu4 -> {
                Log.d("MobileApp", "내비게이션 메뉴 임시 변경")
            }

        }

        return true
    }
}