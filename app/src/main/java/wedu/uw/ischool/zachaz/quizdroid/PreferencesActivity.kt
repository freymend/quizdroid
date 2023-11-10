package wedu.uw.ischool.zachaz.quizdroid

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val urlView = findViewById<EditText>(R.id.url)
        urlView.setText(
            this.getSharedPreferences("quizdroid", Context.MODE_PRIVATE)
                .getString("url", "https://tednewardsandbox.site44.com/questions.json")
        )

        val minutesView = findViewById<EditText>(R.id.minutes)
        minutesView.setText(
            this.getSharedPreferences("quizdroid", Context.MODE_PRIVATE)
                .getString("minutes", "1")
        )

        val saveButton = findViewById<Button>(R.id.save)
        saveButton.setOnClickListener {
            val preferences = this.getSharedPreferences("quizdroid", Context.MODE_PRIVATE)
            preferences.edit().putString("url", urlView.text.toString()).apply()
            preferences.edit().putString("minutes", minutesView.text.toString()).apply()
            finish()
        }
    }
}