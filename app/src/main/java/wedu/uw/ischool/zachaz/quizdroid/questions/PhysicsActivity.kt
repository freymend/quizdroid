package wedu.uw.ischool.zachaz.quizdroid.questions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import wedu.uw.ischool.zachaz.quizdroid.MainActivity
import wedu.uw.ischool.zachaz.quizdroid.R

private var score = 0
private var total = 2

class PhysicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics)

        findViewById<Button>(R.id.beginButton).setOnClickListener {
            val intent = Intent(this, PhysicsQuestionOneActivity::class.java)
            startActivity(intent)
        }
    }
}

class PhysicsQuestionOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_question_one)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, PhysicsAnswerOneActivity::class.java)
            startActivity(intent)
        }

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, _ ->
            submitButton.isEnabled = true
        }
    }
}

class PhysicsAnswerOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_answer_one)

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, PhysicsQuestionTwoActivity::class.java)
            startActivity(intent)
        }
    }
}

class PhysicsQuestionTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_question_two)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, PhysicsAnswerTwoActivity::class.java)
            startActivity(intent)
        }

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, _ ->
            submitButton.isEnabled = true
        }
    }
}

class PhysicsAnswerTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_answer_two)

        val finishButton = findViewById<Button>(R.id.finishButton)
        finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}