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

class MathActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)

        findViewById<Button>(R.id.beginButton).setOnClickListener {
            val intent = Intent(this, MathQuestionOneActivity::class.java)
            startActivity(intent)
        }
    }
}

class MathQuestionOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_question_one)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, MathAnswerOneActivity::class.java)
            startActivity(intent)
        }

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, _ ->
            submitButton.isEnabled = true
        }
    }
}

class MathAnswerOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_answer_one)

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, MathQuestionTwoActivity::class.java)
            startActivity(intent)
        }
    }
}

class MathQuestionTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_question_two)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, MathAnswerTwoActivity::class.java)
            startActivity(intent)
        }

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, _ ->
            submitButton.isEnabled = true
        }
    }
}

class MathAnswerTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_answer_two)

        val finishButton = findViewById<Button>(R.id.finishButton)
        finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}