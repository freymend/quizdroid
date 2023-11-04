package wedu.uw.ischool.zachaz.quizdroid

import android.app.Application
import android.util.Log

data class Quiz(val question: String, val answers: List<String>, val correctAnswer: Int)

data class Topic(val title: String, val description: String, val questions: List<Quiz>)

interface ITopicRepository {
    fun load(): List<Topic>
}

class TopicRepository : ITopicRepository {
    private val topics = load()
    override fun load(): List<Topic> {
        val marvelQuestions = listOf(
            Quiz(
                R.string.marvel_question_1.toString(),
                listOf(
                    R.string.spiderman.toString(),
                    R.string.batman.toString(),
                    R.string.he_man.toString(),
                    R.string.ninjas.toString()
                ),
                0
            ),
            Quiz(
                R.string.marvel_question_2.toString(),
                listOf(
                    R.string.spiderman.toString(),
                    R.string.batman.toString(),
                    R.string.he_man.toString(),
                    R.string.ninjas.toString()
                ),
                0
            )
        )
        val marvel = Topic(
            R.string.marvel_super_heroes.toString(),
            R.string.marvel_description.toString(),
            marvelQuestions
        )

        val physicsQuestions = listOf(
            Quiz(
                R.string.physics_question_1.toString(),
                listOf(
                    R.string.python.toString(),
                    R.string.verb.toString(),
                    R.string.bacteria.toString(),
                    R.string.gravity.toString()
                ),
                3
            ),
            Quiz(
                R.string.physics_question_2.toString(),
                listOf(
                    R.string.python.toString(),
                    R.string.verb.toString(),
                    R.string.bacteria.toString(),
                    R.string.gravity.toString()
                ),
                3
            )
        )
        val physics = Topic(
            R.string.physics.toString(),
            R.string.physics_description.toString(),
            physicsQuestions
        )

        val mathQuestions = listOf(
            Quiz(
                R.string.math_question_1.toString(),
                listOf(
                    R.string.tree.toString(),
                    R.string.algebra.toString(),
                    R.string.earth.toString(),
                    R.string.boom.toString()
                ),
                1
            ),
            Quiz(
                R.string.math_question_2.toString(),
                listOf(
                    R.string.tree.toString(),
                    R.string.algebra.toString(),
                    R.string.earth.toString(),
                    R.string.boom.toString()
                ),
                1
            )
        )
        val math = Topic(
            R.string.math.toString(),
            R.string.math_description.toString(),
            mathQuestions
        )

        return listOf(marvel, physics, math)
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