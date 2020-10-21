package com.ioia.book

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.ioia.book.auth.AuthActivity
//import com.ioia.book.one_signal.NotificationOpenedHandler
//import com.ioia.book.one_signal.NotificationReceivedHandler
//import com.onesignal.OneSignal


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this)
        }else{

            // TODO : Untuk sementara OneSignal dimatikan
//            OneSignal.startInit(this)
//                .setNotificationReceivedHandler(NotificationReceivedHandler())
//                .setNotificationOpenedHandler(NotificationOpenedHandler())
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init()
//
//            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

            val timer = object : CountDownTimer(3000L, 1000L){
                override fun onFinish() {
                    AuthActivity.startActivity(this@MainActivity)
                    finish()
                }

                override fun onTick(millisUntilFinished: Long) {}
            }
            timer.start()
        }
    }
}
