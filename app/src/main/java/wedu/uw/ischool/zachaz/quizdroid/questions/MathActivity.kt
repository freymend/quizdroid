package wedu.uw.ischool.zachaz.quizdroid.questions

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import wedu.uw.ischool.zachaz.quizdroid.MainActivity
import wedu.uw.ischool.zachaz.quizdroid.R

private var score = 0
private var total = 2
private var currentAnswer = ""

class MathActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        findViewById<TextView>(R.id.topic_description).text = resources.getString(R.string.math_description)

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

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { it, _ ->
            currentAnswer = it.findViewById<RadioButton>(it.checkedRadioButtonId).text.toString()
            submitButton.isEnabled = true
            Log.i("currentAnswer", currentAnswer)
        }
    }
}

class MathAnswerOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_answer_one)

        score = 0
        if (currentAnswer == "Algebra") {
            score++
        }

        val answerText = resources.getString(R.string.answered, currentAnswer)
        findViewById<TextView>(R.id.answer).text = answerText

        val correctText = resources.getString(R.string.correct, score, total)
        findViewById<TextView>(R.id.correct).text = correctText

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

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { it, _ ->
            currentAnswer = it.findViewById<RadioButton>(it.checkedRadioButtonId).text.toString()
            submitButton.isEnabled = true
            Log.i("currentAnswer", currentAnswer)
        }
    }
}

class MathAnswerTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_answer_two)

        if (currentAnswer == "Algebra") {
            score++
        }

        val answerText = resources.getString(R.string.answered, currentAnswer)
        findViewById<TextView>(R.id.answer).text = answerText

        val correctText = resources.getString(R.string.correct, score, total)
        findViewById<TextView>(R.id.correct).text = correctText

        val finishButton = findViewById<Button>(R.id.finishButton)
        finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}