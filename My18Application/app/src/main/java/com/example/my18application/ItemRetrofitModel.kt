package com.example.my18application

data class ItemRetrofitModel (
    var rnum: Long = 0,
    var productGb: String? = null,
    var prdlstNm: String? = null,
    var rawmtrl: String? = null,
    var capacity: String? = null,
    var imgurl1: String? = null
)

// 트리 구조 body - header
//       items
//       item item item <json
data class MyItem(val item: ItemRetrofitModel)
data class MyItems(val items:MutableList<MyItem>)
data class MyModel(val body: MyItems)