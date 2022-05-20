package com.divyansh.notificationsdemo

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.divyansh.notificationsdemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiveInput()
    }

    private fun receiveInput(){
        val KEY_REPLY = "key_reply"
        val intent:Intent = this.intent
        val remoteInput: Bundle = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput!=null){
            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            binding.resultTv.text = inputString

            val channelID = "com.divyansh.notificationsdemo.channel1"
            val notificationId = 45

            val repliedNotification = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Your reply received")
                .build()
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)
        }
    }
}