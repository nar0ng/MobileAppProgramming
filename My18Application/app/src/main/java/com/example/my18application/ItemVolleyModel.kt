package com.example.my18application

// mutable 리스트로 전달
// 아답타로 표현
data class ItemVolleyModel (
    // retrofit에서 반드시 필드명이 같아야 한다.
    // 변수명과 json의 필드명
    var id: Long = 0,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null
)