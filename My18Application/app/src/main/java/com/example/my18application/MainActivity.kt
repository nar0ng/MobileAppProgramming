package com.example.my18application

import com.example.my18application.databinding.ActivityAuthBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.my18application.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment
    lateinit var boardFragment: BoardFragment
    // 둘 중에 무엇을 보여주고 있는지 그 값을 저장하는 변수
    var mode = "volley"
    var authMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기화 -> frgment로 쓰기 위해
        volleyFragment = VolleyFragment()
        retrofitFragment = RetrofitFragment()
        boardFragment = BoardFragment()

        // activitymain에 뿌려주기 휘애
        // activty_content-> activiyymain에 있는 frame을 의미한다.
        // 무엇을 실행할것인가?
        supportFragmentManager.beginTransaction()
            // 컨텐트라는 곳에 발리 프래그먼트를 뿌리겠다.
            .replace(R.id.activity_content, volleyFragment)
            // 커밋을 넣어줘야 실행됨
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_content, retrofitFragment)
            .hide(retrofitFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_content, boardFragment)
            .hide(boardFragment)
            .commit()
        // 처음 시작할 때 발리 프레그먼트를 보여주겠다
        supportActionBar?.title = "Volley Test"
    }

    // 옵션 메뉴를 만들었다.
    // onStart 이후 실행
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // onStart에서 실행될 때 초기화가 안 되었다라는 오류
        authMenuItem = menu!!.findItem(R.id.menu_auth)
        if(MyApplication.checkAuth() == true){
            authMenuItem!!.title = "${MyApplication.email}님"
        } else {
            authMenuItem!!.title = "인증"
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onStart() {
        // Intent에서 finish() 된 다음에 돌아올 때 실행
        // 앱 처음 실행될 때
        // onCreate -> onStart -> onCreateOptionsMenu
        super.onStart()
        if (authMenuItem != null){
            if(MyApplication.checkAuth() == true){
                authMenuItem!!.title = "${MyApplication.email}님"
            } else {
                authMenuItem!!.title = "인증"
            }
        }
    }

    // 옵션 메뉴를 누르면 실행
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === R.id.menu_volley && mode !== "volley") {
            supportFragmentManager.beginTransaction()
                // 액티비티 컨텐트에 발리 프래그먼트를 뿌리겠다.
                .show(volleyFragment)
                .commit()
            // 무엇이 클릭됐는가?
            supportFragmentManager.beginTransaction()
                .hide(retrofitFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(boardFragment)
                .commit()
            mode = "volley"
            supportActionBar?.title = "Volley Test"
        } else if (item.itemId === R.id.menu_retrofit && mode !== "retrofit") {
            supportFragmentManager.beginTransaction()
                .show(retrofitFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(volleyFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(boardFragment)
                .commit()
            mode = "retrofit"
            supportActionBar?.title = "Retrofit Test"
        } else if (item.itemId === R.id.menu_board && mode !== "board") {
            supportFragmentManager.beginTransaction()
                .show(boardFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(volleyFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(retrofitFragment)
                .commit()
            mode = "board"
            supportActionBar?.title = "Board Test"
        } else if (item.itemId === R.id.menu_auth) {
            val intent = Intent(this, AuthActivity::class.java)
            if (authMenuItem!!.title!!.equals("인증")) {
                intent.putExtra("data", "logout")
            } else {
                intent.putExtra("data", "login")
            }
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
