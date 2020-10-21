package com.ioia.book.utang

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_hutang")
class Hutang (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,
    var status: Int? = 0,
    var hutang: Long? = 0,
    var customerName: String? = "",
    var customerPhone: String? = "",
    var businessId: Long? = 0,
    var lunas: Boolean? = false,
    var date_created: String? = "",
    var date_updated: String? = ""
){

    constructor(): this(0, 0, 0, "", "", 0, false, "","")

}