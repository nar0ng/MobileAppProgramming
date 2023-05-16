package com.example.my18application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.my18application.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    // 각각의 fragment를 가르키는 변수
    lateinit var volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment

    // 둘 중에 무엇을 보여주고 있는지 그 값을 저장하는 변수
    var mode = "volley"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기화 -> frgment로 쓰기 위해
        volleyFragment = VolleyFragment()
        retrofitFragment = RetrofitFragment()

        // activitymain에 뿌려주기 휘애
        // activty_content-> activiyymain에 있는 frame을 의미한다.
        // 무엇을 실행할것인가?
        supportFragmentManager.beginTransaction()
            // 컨텐트라는 곳에 발리프래그먼트를 뿌리겠다.
            .replace(R.id.activity_content, volleyFragment)
            // 커밋을 넣어줘야 실행됨
            .commit()
        // 처음 시작할 때 발리 프레그먼트를 보여주겠다
        supportActionBar?.title = "Volley Test"
    }

    // 옵션 메뉴를 만들었다.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 옵션 메뉴를 누르면 실행
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 발리가 아닌데 발리가 클릭됐다면
        if (item.itemId === R.id.menu_volley && mode !== "volley") {
            supportFragmentManager.beginTransaction()
                // 액티비티 컨텐트에 발리 프래그먼트를 뿌리겠다.
                .replace(R.id.activity_content, volleyFragment)
                .commit()
            // 무엇이 클릭됐는가?

            mode = "volley"
            supportActionBar?.title = "Volley Test"
        } else if (item.itemId === R.id.menu_retrofit && mode !== "retrofit") {
            // 레트로핏 프래그먼트가 아닌데 레트로핏이 눌렸다
            supportFragmentManager.beginTransaction()
                // 레트로핏으로 바꿔주겠다.
                .replace(R.id.activity_content, retrofitFragment)
                .commit()
            mode = "retrofit"
            supportActionBar?.title = "Retrofit Test"
        }
        return super.onOptionsItemSelected(item)
    }
}
