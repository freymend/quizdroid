package wedu.uw.ischool.zachaz.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataset = arrayOf("Math", "Physics", "Marvel Super Heroes")
        val quizAdapter = QuizAdapter(dataset)

        val recyclerView = findViewById<RecyclerView>(R.id.quiz_list)
        recyclerView.adapter = quizAdapter
    }
}

class QuizAdapter(private val dataSet: Array<String>): RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.quiz_title)

        fun bind(word: String) {
            textView.text = word
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.quiz_list_item, viewGroup, false)
        Log.i("QuizAdapter", "Created new view")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
        Log.i("QuizAdapter", "Bound view to position $position")
    }

    override fun getItemCount() = dataSet.size
}
