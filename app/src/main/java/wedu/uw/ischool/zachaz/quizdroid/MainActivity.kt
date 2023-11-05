package wedu.uw.ischool.zachaz.quizdroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wedu.uw.ischool.zachaz.quizdroid.questions.TopicActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        QuizApp.repository.load(this)

        val quizAdapter = QuizAdapter(this)

        val recyclerView = findViewById<RecyclerView>(R.id.quiz_list)
        recyclerView.adapter = quizAdapter

    }
}

class QuizAdapter(private val context: Context) : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
    private val dataSet = { QuizApp.repository.getTopicsTitles() }

    class ViewHolder(private val context: Context, view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.quiz_title)

        init {
            view.setOnClickListener {
                val intent = Intent(context, TopicActivity::class.java)
                intent.putExtra("topic", textView.text.toString())

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
        holder.bind(dataSet()[position])
    }

    override fun getItemCount() = dataSet().size
}
