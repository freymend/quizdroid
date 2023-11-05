package wedu.uw.ischool.zachaz.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log
import java.io.Serializable

data class Quiz(val question: String, val answers: List<String>, val correctAnswer: Int) : Serializable

data class Topic(val title: String, val description: String, val questions: List<Quiz>) : Serializable

interface ITopicRepository {
    fun load(context: Context)

    fun getTopicsTitles(): List<String>

    fun getTopic(topic: String): Topic

    fun getQuiz(topic: String, index: Int): Quiz
}

class TopicRepository : ITopicRepository {
    private lateinit var topics: List<Topic>

    override fun load(context: Context) {
        val marvelQuestions = listOf(
            Quiz(
                context.getString(R.string.marvel_question_1),
                listOf(
                    context.getString(R.string.spiderman),
                    context.getString(R.string.batman),
                    context.getString(R.string.he_man),
                    context.getString(R.string.ninjas)
                ),
                0
            ),
            Quiz(
                context.getString(R.string.marvel_question_2),
                listOf(
                    context.getString(R.string.spiderman),
                    context.getString(R.string.batman),
                    context.getString(R.string.he_man),
                    context.getString(R.string.ninjas)
                ),
                0
            )
        )
        val marvel = Topic(
            context.getString(R.string.marvel_super_heroes),
            context.getString(R.string.marvel_description),
            marvelQuestions
        )

        val physicsQuestions = listOf(
            Quiz(
                context.getString(R.string.physics_question_1),
                listOf(
                    context.getString(R.string.python),
                    context.getString(R.string.verb),
                    context.getString(R.string.bacteria),
                    context.getString(R.string.gravity)
                ),
                3
            ),
            Quiz(
                context.getString(R.string.physics_question_2),
                listOf(
                    context.getString(R.string.python),
                    context.getString(R.string.verb),
                    context.getString(R.string.bacteria),
                    context.getString(R.string.gravity)
                ),
                3
            )
        )
        val physics = Topic(
            context.getString(R.string.physics),
            context.getString(R.string.physics_description),
            physicsQuestions
        )

        val mathQuestions = listOf(
            Quiz(
                context.getString(R.string.math_question_1),
                listOf(
                    context.getString(R.string.tree),
                    context.getString(R.string.algebra),
                    context.getString(R.string.earth),
                    context.getString(R.string.boom)
                ),
                1
            ),
            Quiz(
                context.getString(R.string.math_question_2),
                listOf(
                    context.getString(R.string.tree),
                    context.getString(R.string.algebra),
                    context.getString(R.string.earth),
                    context.getString(R.string.boom)
                ),
                1
            )
        )
        val math = Topic(
            context.getString(R.string.math),
            context.getString(R.string.math_description),
            mathQuestions
        )
        topics = listOf(marvel, physics, math)
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