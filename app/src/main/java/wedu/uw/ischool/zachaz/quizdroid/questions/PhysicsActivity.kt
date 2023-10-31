package wedu.uw.ischool.zachaz.quizdroid.questions

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import wedu.uw.ischool.zachaz.quizdroid.R

class PhysicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics)

        findViewById<Button>(R.id.beginButton).setOnClickListener {
            val intent = Intent(this, PhysicsQuestionActivity::class.java)
            startActivity(intent)
        }
    }
}

class PhysicsQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_question)

    }
}