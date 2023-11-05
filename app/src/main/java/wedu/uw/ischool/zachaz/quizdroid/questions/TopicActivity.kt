package wedu.uw.ischool.zachaz.quizdroid.questions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import wedu.uw.ischool.zachaz.quizdroid.MainActivity
import wedu.uw.ischool.zachaz.quizdroid.QuizApp
import wedu.uw.ischool.zachaz.quizdroid.R
import wedu.uw.ischool.zachaz.quizdroid.Topic

class TopicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        intent.extras?.let {
            val topic = QuizApp.repository.getTopic(it.getString("topic").toString())
            findViewById<TextView>(R.id.topic_description).text =
                topic.description

            findViewById<Button>(R.id.beginButton).setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("topic", topic)
                intent.putExtra("index", 0)
                intent.putExtra("score", 0)
                startActivity(intent)
            }
        }
    }
}

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_question)

        intent.extras?.let {
            val topic = it["topic"] as Topic
            val quizzes = topic.questions
            val index = it.getInt("index")
            val score = it.getInt("score")

            val quiz = quizzes[index]
            findViewById<TextView>(R.id.question).text = quiz.question

            val answerGroup = findViewById<RadioGroup>(R.id.answerGroup)
            answerGroup.findViewById<RadioButton>(R.id.answer_1).text = quiz.answers[0]
            answerGroup.findViewById<RadioButton>(R.id.answer_2).text = quiz.answers[1]
            answerGroup.findViewById<RadioButton>(R.id.answer_3).text = quiz.answers[2]
            answerGroup.findViewById<RadioButton>(R.id.answer_4).text = quiz.answers[3]

            val submitButton = findViewById<Button>(R.id.submitButton)
            var selectedIndex: Int? = null
            submitButton.setOnClickListener {
                val intent = Intent(this, AnswerActivity::class.java)
                intent.putExtra("topic", topic)
                intent.putExtra("index", index)
                intent.putExtra("score", score)
                intent.putExtra("answer", selectedIndex!!)
                startActivity(intent)
            }

            answerGroup.setOnCheckedChangeListener { group, _ ->
                submitButton.isEnabled = true
                val selectedButton = group.findViewById<RadioButton>(group.checkedRadioButtonId)
                selectedIndex = group.indexOfChild(selectedButton)
            }
        }
    }
}

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_answer)

        intent.extras?.let {
            val topic = it["topic"] as Topic
            val quizzes = topic.questions
            val answer = it.getInt("answer")
            var index = it.getInt("index")
            var score = it.getInt("score")

            val quiz = quizzes[index]

            if (answer == quiz.correctAnswer) {
                score++
            }

            val correctAnswer = resources.getString(R.string.correct_answer, quiz.answers[quiz.correctAnswer])
            findViewById<TextView>(R.id.answer_text).text = correctAnswer

            val answerText = resources.getString(R.string.answered, quiz.answers[answer])
            findViewById<TextView>(R.id.response).text = answerText

            val correctText = resources.getString(R.string.correct, score, quizzes.size)
            findViewById<TextView>(R.id.number_correct).text = correctText

            val nextButton = findViewById<Button>(R.id.nextButton)
            val intent = when {
                index < quizzes.size - 1 -> {
                    val intent = Intent(this, QuestionActivity::class.java)
                    index++
                    intent.putExtra("topic", topic)
                    intent.putExtra("index", index)
                    intent.putExtra("score", score)
                    intent
                }
                else -> {
                    nextButton.text = resources.getString(R.string.finish)
                    Intent(this, MainActivity::class.java)
                }
            }

            nextButton.setOnClickListener {
                startActivity(intent)
            }
        }
    }
}