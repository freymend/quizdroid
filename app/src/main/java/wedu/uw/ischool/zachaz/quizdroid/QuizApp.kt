package wedu.uw.ischool.zachaz.quizdroid

import android.app.Application
import android.content.Context
import android.os.Environment
import android.util.Log
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.io.File
import java.io.Serializable

data class Quiz(val question: String, val answers: List<String>, val correctAnswer: Int) :
    Serializable

data class Topic(val title: String, val description: String, val questions: List<Quiz>) :
    Serializable

interface ITopicRepository {
    fun load(context: Context)

    fun add(topic: Topic)

    fun getTopicsTitles(): List<String>

    fun getTopic(topic: String): Topic

    fun getQuiz(topic: String, index: Int): Quiz
}

class TopicRepository : ITopicRepository {
    private var topics = mutableListOf<Topic>()

    override fun load(context: Context) {
        val jsonData = getJSON(context)

        val topics = MutableList(jsonData.length()) { topic ->
            val jsonTopic = jsonData.getJSONObject(topic)
            val title = jsonTopic.getString("title")
            val desc = jsonTopic.getString("desc")

            val jsonQuestions = jsonTopic.getJSONArray("questions")
            val quizList = List(jsonQuestions.length()) { question ->
                val jsonQuestion = jsonQuestions.getJSONObject(question)
                val questionText = jsonQuestion.getString("text")
                val correctAnswer = jsonQuestion.getInt("answer")

                val jsonAnswers = jsonQuestion.getJSONArray("answers")
                val answerList =
                    List<String>(jsonAnswers.length()) { answer ->
                        jsonAnswers.getString(answer)
                    }
                Quiz(questionText, answerList, correctAnswer)
            }
            Topic(title, desc, quizList)
        }

        this.topics = topics
    }

    private fun getJSON(context: Context) : JSONArray {
        return runBlocking {
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "questions.json")
            if (file.exists()) {
                return@runBlocking JSONArray(file.readText())
            }
            context.assets.open("questions.json").bufferedReader().use {
                JSONArray(it.readText())
            }
        }
    }

    override fun getTopicsTitles(): List<String> {
        return topics.map { topic -> topic.title }
    }

    override fun add(topic: Topic) {
        topics.add(topic)
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