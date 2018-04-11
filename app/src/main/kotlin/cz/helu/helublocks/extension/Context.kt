package cz.helu.helublocks.extension

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.provider.Settings


val Context.notificationManager: NotificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


@SuppressLint("HardwareIds")
fun Context.deviceID(): String = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)