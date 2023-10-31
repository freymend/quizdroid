package wedu.uw.ischool.zachaz.quizdroid.questions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import wedu.uw.ischool.zachaz.quizdroid.MainActivity
import wedu.uw.ischool.zachaz.quizdroid.R

private var score = 0
private var total = 2
private var currentAnswer= ""

class MarvelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel)

        findViewById<Button>(R.id.beginButton).setOnClickListener {
            val intent = Intent(this, MarvelQuestionOneActivity::class.java)
            startActivity(intent)
        }

    }
}

class MarvelQuestionOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_question_one)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, MarvelAnswerOneActivity::class.java)
            startActivity(intent)
        }

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { it, _ ->
            currentAnswer = it.findViewById<RadioButton>(it.checkedRadioButtonId).text.toString()
            submitButton.isEnabled = true
        }
    }
}

class MarvelAnswerOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_answer_one)

        score = 0
        if (currentAnswer == "Spiderman") {
            score++
        }

        val answerText = resources.getString(R.string.answered, currentAnswer)
        findViewById<TextView>(R.id.answer).text = answerText

        val correctText = resources.getString(R.string.correct, score, total)
        findViewById<TextView>(R.id.correct).text = correctText

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, MarvelQuestionTwoActivity::class.java)
            startActivity(intent)
        }
    }
}

class MarvelQuestionTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_question_two)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, MarvelAnswerTwoActivity::class.java)
            startActivity(intent)
        }

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { it, _ ->
            currentAnswer = it.findViewById<RadioButton>(it.checkedRadioButtonId).text.toString()
            submitButton.isEnabled = true
        }
    }
}

class MarvelAnswerTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_answer_two)

        if (currentAnswer == "Spiderman") {
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