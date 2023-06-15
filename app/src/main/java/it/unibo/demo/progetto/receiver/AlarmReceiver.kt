package it.unibo.demo.progetto.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import it.unibo.demo.progetto.R
import it.unibo.demo.progetto.util.sendNotification

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        // TODO: a riga 22 dentro alle tonde di .getText dobbiamo passare il body della notifica(es:partita che inizia)
        notificationManager.sendNotification(
            context.getText(R.string.body_da_cambiare).toString(),
            context
        )

    }

}