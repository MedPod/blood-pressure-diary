package com.zhukofff.medpod

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.zhukofff.medpod.databinding.FragmentSettingsBinding
import java.util.*

class SettingsFragment: Fragment() {

    // уведомления
    lateinit var notificationManager: NotificationManager
    lateinit var alarmManager: AlarmManager


    private lateinit var binding: FragmentSettingsBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("WrongViewCast")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //alarm manager
        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager


        // при включении появляется окошко со временем и кнопка установить
        var switchCheck = false

        binding.switchOnOff.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.switchOnOff.setText("ON")
                binding.setTime.visibility = View.VISIBLE
                binding.time.visibility = View.VISIBLE
                switchCheck = true
            } else {
                binding.switchOnOff.setText("OFF")
                binding.time.visibility = View.INVISIBLE
                binding.setTime.visibility = View.INVISIBLE
                switchCheck = false
            }
        }

        // диспетчер уведомлений
        val manager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        // установка напоминаний об измерении давления

        binding.setTime.setOnClickListener {

            // время уведомления
            var hour = binding.time.hour.toInt() * 60 * 60 * 1000 // милисекунды
            var minute = binding.time.minute.toInt() * 60 * 1000
            var milliseconds =
                hour + minute

            // сколько добавить к текущему времени
            val calendar = Calendar.getInstance()
            val calendarHour = calendar.get(Calendar.HOUR_OF_DAY)
            val calendarMinute = calendar.get(Calendar.MINUTE)

            // текущее время в миллисекундах
            var nowMilliseconds =
                calendarHour * 60 * 60 * 1000 + calendarMinute * 60 * 1000

            // добавление времени с момента установки уведомления
            var timeToAdd = 0//
            if (milliseconds > nowMilliseconds) {
                timeToAdd = milliseconds - nowMilliseconds
            } else {
                timeToAdd = (24 * 60 * 60 * 1000 - nowMilliseconds) + milliseconds
            }

            // уведомление в верхней панеле
            val notifyIntent = Intent(context, Receiver::class.java)
            var pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            // установка времени и интервала напоминаний
            val alarmManager =
                context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            // повторяем уведомления каждые 24 часа
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeToAdd,
                1000 * 60 * 60 * 24.toLong(), pendingIntent
            )


        }


    }


// класс для отображения уведомлений о синхронизированных измерениях
class Receiver() : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val intent1 = Intent(context, MyNewIntentService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(intent1)
        } else {
            context!!.startService(intent1)
        }


    }


}

class MyNewIntentService : IntentService("MyNewIntentService") {


    private val channelId = NotificationChannel.DEFAULT_CHANNEL_ID
    private val description = "Тестовое уведомление"

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground()
        else startForeground(1, Notification())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyOwnForeground() {
        val NOTIFICATION_CHANNEL_ID = "com.zhukofff.medpod"
        val channelName = "Мой сервис"
        val chan = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager =
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)
        val notificationBuilder =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.logo_medpod)
            .setContentTitle("Приложение запущено фоном")
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(2, notification)
    }

    override fun onHandleIntent(intent: Intent?) {
        var builder = Notification.Builder(this)
        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intentN = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intentN, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setContentTitle("Уведомление об измерениях")
                .setContentText("Самое время измерить артериальное давление!")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.mipmap.ic_launcher
                    )
                )
        } else {

            builder = Notification.Builder(this)
                .setContentTitle("Уведомление об измерениях")
                .setContentText("Самое время измерить артриальное давление!")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.mipmap.ic_launcher
                    )
                )
        }
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)
        notificationManager.notify(1234, builder.build())

    }
}
}