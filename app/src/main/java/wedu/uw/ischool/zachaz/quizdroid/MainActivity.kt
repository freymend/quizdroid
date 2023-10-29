package wedu.uw.ischool.zachaz.quizdroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wedu.uw.ischool.zachaz.quizdroid.questions.MarvelActivity
import wedu.uw.ischool.zachaz.quizdroid.questions.MathActivity
import wedu.uw.ischool.zachaz.quizdroid.questions.PhysicsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataset = resources.getStringArray(R.array.quiz_titles)
        val quizAdapter = QuizAdapter(this, dataset)

        val recyclerView = findViewById<RecyclerView>(R.id.quiz_list)
        recyclerView.adapter = quizAdapter

    }
}

class QuizAdapter(private val context: Context, private val dataSet: Array<String>)
    : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    class ViewHolder(private val context: Context, view: View): RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.quiz_title)

        init {
            view.setOnClickListener {
                val intent = when (textView.text) {
                    "Math" -> Intent(context, MathActivity::class.java)
                    "Marvel Super Heroes" -> Intent(context, MarvelActivity::class.java)
                    else -> Intent(context, PhysicsActivity::class.java)
                }
                ContextCompat.startActivity(context, intent, null)
            }
        }
        fun bind(word: String) {
            textView.text = word
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.quiz_list_item, viewGroup, false)
        return ViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}
