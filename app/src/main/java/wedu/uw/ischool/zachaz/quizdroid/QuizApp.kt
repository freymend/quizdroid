package wedu.uw.ischool.zachaz.quizdroid

import android.app.Application
import android.util.Log

data class Quiz(val question: String, val answers: List<String>, val correctAnswer: Int)

data class Topic(val title: String, val description: String, val questions: List<Quiz>)

interface ITopicRepository {
    fun load()
}

class TopicRepository : ITopicRepository {
    override fun load() {
        // TODO: Not yet implemented
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