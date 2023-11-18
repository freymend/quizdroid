package wedu.uw.ischool.zachaz.quizdroid

import android.app.AlarmManager
import android.app.DownloadManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.*
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import wedu.uw.ischool.zachaz.quizdroid.questions.TopicActivity
import java.io.File

const val ALARM_ACTION = "edu.uw.ischool.zachaz.awty.DOWNLOAD"

class MainActivity : AppCompatActivity() {
    private var receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.quiz_toolbar))

        loadPreferences()

        if (airplaneMode()) {
            Log.i("QuizApp", "Airplane mode is on")
            MaterialAlertDialogBuilder(this)
                .setTitle("Airplane Mode")
                .setMessage("Airplane mode is on")
                .setNeutralButton("OK") { _, _ -> }
                .setPositiveButton("Turn off") { _, _ ->
                    startActivity(Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS))
                }
                .show()
        } else if (networkStatus()) {
            Toast.makeText(
                this, this.getSharedPreferences("quizdroid", Context.MODE_PRIVATE).getString(
                    "url",
                    "https://tednewardsandbox.site44.com/questions.json"
                ), Toast.LENGTH_LONG
            ).show()
            receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    try {
                        downloadJSON()

                        QuizApp.repository.load(applicationContext)

                        val quizAdapter = QuizAdapter(applicationContext)

                        val recyclerView = findViewById<RecyclerView>(R.id.quiz_list)
                        recyclerView.adapter = quizAdapter
                    } catch (e: Exception) {
                        MaterialAlertDialogBuilder(applicationContext)
                            .setTitle("Error")
                            .setMessage("Error downloading JSON")
                            .setPositiveButton("Try Again") { _, _ ->
                                val restartIntent = Intent.makeRestartActivityTask(
                                    applicationContext.packageManager.getLaunchIntentForPackage(
                                        applicationContext.packageName
                                    )!!.component
                                )
                                applicationContext.startActivity(restartIntent)
                                Runtime.getRuntime().exit(0)
                            }
                            .setNegativeButton("Quit") { _, _ -> finish() }
                            .show()
                    }
                }
            }

            val filter = IntentFilter(ALARM_ACTION)
            registerReceiver(receiver, filter)

            val intent = Intent(ALARM_ACTION)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                getSharedPreferences("quizdroid", Context.MODE_PRIVATE).getString("minutes", "1")!!
                    .toLong() * 60 * 1000,
                pendingIntent
            )

        } else {
            Log.i("QuizApp", "Network is not available")
            MaterialAlertDialogBuilder(this)
                .setTitle("Network Error")
                .setMessage("Network is not available")
                .setPositiveButton("OK") { _, _ -> }
                .show()
        }
    }

    private fun downloadJSON() {
        val downloadManager =
            getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val uri = android.net.Uri.parse(
            this.getSharedPreferences("quizdroid", Context.MODE_PRIVATE)
                .getString("url", "https://tednewardsandbox.site44.com/questions.json")
        )

        val file =
            File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "temp_questions.json")

        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalFilesDir(
            this,
            Environment.DIRECTORY_DOWNLOADS,
            "temp_questions.json"
        )

        downloadManager.enqueue(request)

        val questionsFile =
            File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "questions.json")
        if (questionsFile.exists()) {
            file.copyTo(questionsFile, true)
            file.delete()
        } else {
            file.renameTo(questionsFile)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("QuizApp", "QuizApp Application Resumed")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("QuizApp", "QuizApp Application Restarted")
    }

    private fun airplaneMode(): Boolean {
        return android.provider.Settings.System.getInt(
            this.contentResolver,
            android.provider.Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0
    }

    private fun networkStatus(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities?.let {
            return it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        return false
    }

    private fun loadPreferences() {
        val preferences = this.getSharedPreferences("quizdroid", Context.MODE_PRIVATE)
        preferences.getString("url", "https://tednewardsandbox.site44.com/questions.json")?.let {
            preferences.edit().putString("url", it).apply()
        }
        preferences.getString("minutes", "1").let {
            preferences.edit().putString("minutes", it).apply()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.preferences -> {
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // The user's action isn't recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}

class QuizAdapter(private val context: Context) : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
    private val dataSet = { QuizApp.repository.getTopicsTitles() }

    class ViewHolder(private val context: Context, view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.quiz_title)

        init {
            view.setOnClickListener {
                val intent = Intent(context, TopicActivity::class.java)
                intent.putExtra("topic", textView.text.toString())

                ContextCompat.startActivity(context, intent, null)
            }
        }

        fun bind(word: String) {
            textView.text = word
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.quiz_list_item, viewGroup, false)
        return ViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet()[position])
    }

    override fun getItemCount() = dataSet().size
}
