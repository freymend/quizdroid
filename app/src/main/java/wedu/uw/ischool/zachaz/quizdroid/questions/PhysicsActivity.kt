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

class PhysicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        findViewById<TextView>(R.id.topic_description).text = resources.getString(R.string.physics_description)

        findViewById<Button>(R.id.beginButton).setOnClickListener {
            val intent = Intent(this, PhysicsQuestionOneActivity::class.java)
            startActivity(intent)
        }
    }
}

class PhysicsQuestionOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_question)

        findViewById<TextView>(R.id.question).text = resources.getString(R.string.physics_question_1)

        val answerGroup = findViewById<RadioGroup>(R.id.answerGroup)
        answerGroup.findViewById<RadioButton>(R.id.answer_1).text = resources.getString(R.string.python)
        answerGroup.findViewById<RadioButton>(R.id.answer_2).text = resources.getString(R.string.verb)
        answerGroup.findViewById<RadioButton>(R.id.answer_3).text = resources.getString(R.string.bacteria)
        answerGroup.findViewById<RadioButton>(R.id.answer_4).text = resources.getString(R.string.gravity)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, PhysicsAnswerOneActivity::class.java)
            startActivity(intent)
        }

        answerGroup.setOnCheckedChangeListener { it, _ ->
            currentAnswer = it.findViewById<RadioButton>(it.checkedRadioButtonId).text.toString()
            submitButton.isEnabled = true
        }
    }
}

class PhysicsAnswerOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_answer_one)

        score = 0
        if (currentAnswer == "Gravity") {
            score++
        }

        val answerText = resources.getString(R.string.answered, currentAnswer)
        findViewById<TextView>(R.id.answer).text = answerText

        val correctText = resources.getString(R.string.correct, score, total)
        findViewById<TextView>(R.id.correct).text = correctText

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
        setContentView(R.layout.activity_topic_question)

        findViewById<TextView>(R.id.question).text = resources.getString(R.string.physics_question_2)

        val answerGroup = findViewById<RadioGroup>(R.id.answerGroup)
        answerGroup.findViewById<RadioButton>(R.id.answer_1).text = resources.getString(R.string.python)
        answerGroup.findViewById<RadioButton>(R.id.answer_2).text = resources.getString(R.string.verb)
        answerGroup.findViewById<RadioButton>(R.id.answer_3).text = resources.getString(R.string.bacteria)
        answerGroup.findViewById<RadioButton>(R.id.answer_4).text = resources.getString(R.string.gravity)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, PhysicsAnswerTwoActivity::class.java)
            startActivity(intent)
        }

        answerGroup.setOnCheckedChangeListener { it, _ ->
            currentAnswer = it.findViewById<RadioButton>(it.checkedRadioButtonId).text.toString()
            submitButton.isEnabled = true
        }
    }
}

class PhysicsAnswerTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_answer_two)

        if (currentAnswer == "Gravity") {
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