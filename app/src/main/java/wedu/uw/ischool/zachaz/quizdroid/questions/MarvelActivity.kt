package wedu.uw.ischool.zachaz.quizdroid.questions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import wedu.uw.ischool.zachaz.quizdroid.R

class MarvelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel)

        findViewById<Button>(R.id.beginButton).setOnClickListener {
            val intent = Intent(this, MarvelQuestionActivity::class.java)
            startActivity(intent)
        }

    }
}

class MarvelQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_question)

    }
}