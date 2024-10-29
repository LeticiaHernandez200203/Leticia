package com.example.notificacionlocal

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.channels.Channel

class MainActivity : AppCompatActivity() {
    private val channel_id="1000"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val boton = findViewById<Button>(R.id.btnNotificaciones)
        boton.setOnClickListener()
        {
createNot()
            enviarNot()
        }
    }
    private fun createNot()
    {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  {
        val name = "Canal 1000"
        val descri = "canal de Notificaciones"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val Channel = NotificationChannel(channel_id,name,importance).apply {
            description=descri
        }
val notificationManager : NotificationManager= getSystemService(NOTIFICATION_SERVICE) as
        NotificationManager
       notificationManager.createNotificationChannel(Channel)

    }
    }
    private fun enviarNot() {
        val mensaje = "Bienvenida a nuestra app de cosméticos, ¡Espero encuentres lo que buscas!"
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
            !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),1)

        }
        else

        {

          val intent = Intent(this,MainActivity2::class.java).apply {
              putExtra("notification_message", mensaje)

          }

          val pendingIntent: PendingIntent= PendingIntent.getActivity(
              this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)




        val builder = NotificationCompat.Builder(this,channel_id)
        .setSmallIcon(R.drawable.ic_reloj)
        .setContentTitle("Pruebas")
        .setContentText(mensaje)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        Log.d("MainActivity", "Notifiacion Enviada")
        with(NotificationManagerCompat.from(this)){
            notify(1, builder.build())

        }
    }


        }
    }