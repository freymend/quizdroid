package wedu.uw.ischool.zachaz.quizdroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
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
        setSupportActionBar(findViewById(R.id.quiz_toolbar))

        QuizApp.repository.load(this)

        val quizAdapter = QuizAdapter(this)

        val recyclerView = findViewById<RecyclerView>(R.id.quiz_list)
        recyclerView.adapter = quizAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.preferences -> {
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // The user's action isn't recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
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
