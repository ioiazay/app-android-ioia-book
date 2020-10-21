package com.ioia.book.fcm

class FCMData(
    var to: String? = "",
    var notification: Notification? = null,
    var data: Data? = null
){

    class Notification(
        var title: String? = "",
        var body: String? = ""
    )

    class Data(
        var id: String? = ""
    )

}