package com.example.my12application

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 클래스의 기본 틀
class DBHelper(context: Context): SQLiteOpenHelper(context,"testdb", null, 1 ) {
    override fun onCreate(db: SQLiteDatabase?) {
        // 전달받은 데이터베이스를 이용해서
        // 괄호안에 필드의 레코드 넣어주기, 변수처럼 사용
        // 레코드: 행
        // 필드: 열
        db?.execSQL("create table todo_tbl (_id integer primary key autoincrement, todo not null)")
        // id, todo
        // autoincrement: 데이터가 들어올 때 마다 자동으로 번호가 부여된다
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}