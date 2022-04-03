package ir.moris.interviewapp.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.app.TaskStackBuilder
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import ir.moris.interviewapp.App.Companion.CHANNEL_ID
import ir.moris.interviewapp.MainActivity
import ir.moris.interviewapp.R
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.thread

class ActiveAppService : Service() {

    private var lastApp = ""
    lateinit var scope : Job

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val pendingIntent = NavDeepLinkBuilder(this)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.serviceFragment)
            .createPendingIntent()


        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Active apps service")
            .setContentText("if you open an app , I will make a toast message and say which app is open on the screen")
            .setSmallIcon(R.drawable.ic_active_app)
            .setContentIntent(pendingIntent)
            .build()

        /*//convert into coroutine
        Thread(Runnable {
            while (true){
                try {
                    Thread.sleep(100)
                }catch (e : Exception){}
                val currentApp = getForegroundAppName()
                if (currentApp!= lastApp){
                    println(currentApp)
                    lastApp = currentApp
                }
            }
        }).start()*/

        scope = GlobalScope.launch {
            while (true) {
                try {
                    delay(10)
                } catch (e: Exception) {
                }
                val currentApp = getForegroundAppName()
                if (currentApp != lastApp) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ActiveAppService , currentApp , Toast.LENGTH_SHORT).show()
                    }
                    lastApp = currentApp
                }
            }
        }

        startForeground(1, notification)
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun getForegroundAppName(): String {

        var currentApp = "NULL"
        val usm = this.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val time = System.currentTimeMillis()
        val appList =
            usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 1000, time)
        if (appList != null && appList.size > 0) {
            val mySortedMap: SortedMap<Long, UsageStats> = TreeMap<Long, UsageStats>()
            for (usageStatus in appList) {
                mySortedMap.put(usageStatus.lastTimeUsed, usageStatus)
            }
            if (mySortedMap != null && !mySortedMap.isEmpty()) {
                currentApp = mySortedMap.get(mySortedMap.lastKey())!!.getPackageName()
            }
        }
        return currentApp.split(".").last()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }


}