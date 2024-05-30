package com.example.canuwashme;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentConfirm = new Intent(context, Bienvenida.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentConfirm, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyCanal")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Recordatorio de Cita")
                .setContentText("Recuerda que estamos próximos a llegar con el servicio solicitado")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_confirm, "Confirmar", pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}
