package wedu.uw.ischool.zachaz.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.Serializable
import java.net.URL

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
        if (topics.isNotEmpty()) {
            return
        }

        val jsonData = context.assets.open("questions.json").bufferedReader().use {
            val text = it.readText()
            JSONArray(text)
        }

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

    private fun getJSON() {
        val jsonData = runBlocking {
            val url = URL("https://tednewardsandbox.site44.com/questions.json")
            withContext(Dispatchers.IO) {
                url.openStream()
            }.bufferedReader().use {
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