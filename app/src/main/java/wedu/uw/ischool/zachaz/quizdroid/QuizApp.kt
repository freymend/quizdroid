package wedu.uw.ischool.zachaz.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.Serializable

data class Quiz(val question: String, val answers: List<String>, val correctAnswer: Int) :
    Serializable

data class Topic(val title: String, val description: String, val questions: List<Quiz>) :
    Serializable

interface ITopicRepository {
    fun load(context: Context)

    fun getTopicsTitles(): List<String>

    fun getTopic(topic: String): Topic

    fun getQuiz(topic: String, index: Int): Quiz
}

class TopicRepository : ITopicRepository {
    private var topics: List<Topic> = mutableListOf()

    override fun load(context: Context) {
        if (topics.isNotEmpty()) {
            return
        }

        val jsonData = context.assets.open("questions.json").bufferedReader().use {
            val text = it.readText()
            JSONArray(text)
        }

        for (i in 0 until jsonData.length()) {
            val topic = jsonData.getJSONObject(i)

            val title = topic.getString("title")
            val desc = topic.getString("desc")

            val questions = topic.getJSONArray("questions")

            val quizList = mutableListOf<Quiz>()

            for (j in 0 until questions.length()) {
                val question = questions.getJSONObject(j)

                val questionText = question.getString("text")
                val answers = question.getJSONArray("answers")

                val answerList = mutableListOf<String>()

                for (k in 0 until answers.length()) {
                    answerList.add(answers.getString(k))
                }
                val correctAnswer = question.getInt("answer")
                quizList.add(Quiz(questionText, answerList, correctAnswer))
            }
            topics += (listOf(Topic(title, desc, quizList)))
        }
    }

    override fun getTopicsTitles(): List<String> {
        return topics.map { topic -> topic.title }
    }

    override fun getTopic(topic: String): Topic {
        return topics.find { it.title == topic }!!
    }

    override fun getQuiz(topic: String, index: Int): Quiz {
        return getTopic(topic).questions[index]
    }
}

class QuizApp : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: QuizApp
            private set
        val repository = TopicRepository()
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp", "QuizApp Application Started")
    }
}