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
private var currentAnswer = ""

class MarvelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        findViewById<TextView>(R.id.topic_description).text =
            resources.getString(R.string.marvel_description)

        findViewById<Button>(R.id.beginButton).setOnClickListener {
            val intent = Intent(this, MarvelQuestionOneActivity::class.java)
            startActivity(intent)
        }
    }
}

class MarvelQuestionOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_question)

        findViewById<TextView>(R.id.question).text = resources.getString(R.string.marvel_question_1)

        val answerGroup = findViewById<RadioGroup>(R.id.answerGroup)
        answerGroup.findViewById<RadioButton>(R.id.answer_1).text =
            resources.getString(R.string.spiderman)
        answerGroup.findViewById<RadioButton>(R.id.answer_2).text =
            resources.getString(R.string.batman)
        answerGroup.findViewById<RadioButton>(R.id.answer_3).text =
            resources.getString(R.string.he_man)
        answerGroup.findViewById<RadioButton>(R.id.answer_4).text =
            resources.getString(R.string.ninjas)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, MarvelAnswerOneActivity::class.java)
            startActivity(intent)
        }

        answerGroup.setOnCheckedChangeListener { it, _ ->
            currentAnswer = it.findViewById<RadioButton>(it.checkedRadioButtonId).text.toString()
            submitButton.isEnabled = true
        }
    }
}

class MarvelAnswerOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_answer)

        findViewById<TextView>(R.id.answer_text).text =
            resources.getString(R.string.marvel_answer_1)

        score = 0
        if (currentAnswer == "Spiderman") {
            score++
        }

        val answerText = resources.getString(R.string.answered, currentAnswer)
        findViewById<TextView>(R.id.response).text = answerText

        val correctText = resources.getString(R.string.correct, score, total)
        findViewById<TextView>(R.id.number_correct).text = correctText

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
        setContentView(R.layout.activity_topic_question)

        findViewById<TextView>(R.id.question).text = resources.getString(R.string.marvel_question_2)

        val answerGroup = findViewById<RadioGroup>(R.id.answerGroup)
        answerGroup.findViewById<RadioButton>(R.id.answer_1).text =
            resources.getString(R.string.spiderman)
        answerGroup.findViewById<RadioButton>(R.id.answer_2).text =
            resources.getString(R.string.batman)
        answerGroup.findViewById<RadioButton>(R.id.answer_3).text =
            resources.getString(R.string.he_man)
        answerGroup.findViewById<RadioButton>(R.id.answer_4).text =
            resources.getString(R.string.ninjas)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, MarvelAnswerTwoActivity::class.java)
            startActivity(intent)
        }

        answerGroup.setOnCheckedChangeListener { it, _ ->
            currentAnswer = it.findViewById<RadioButton>(it.checkedRadioButtonId).text.toString()
            submitButton.isEnabled = true
        }
    }
}

class MarvelAnswerTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_answer)

        findViewById<TextView>(R.id.answer_text).text =
            resources.getString(R.string.marvel_answer_2)

        if (currentAnswer == "Spiderman") {
            score++
        }

        val answerText = resources.getString(R.string.answered, currentAnswer)
        findViewById<TextView>(R.id.response).text = answerText

        val correctText = resources.getString(R.string.correct, score, total)
        findViewById<TextView>(R.id.number_correct).text = correctText

        val finishButton = findViewById<Button>(R.id.nextButton)
        finishButton.text = resources.getString(R.string.finish)
        finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}