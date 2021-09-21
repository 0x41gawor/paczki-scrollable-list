package pl.gawor.android.tutorials.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pl.gawor.android.tutorials.affirmations.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        // in Kotlin we do not have `new` keyword
        // in Kotlin we can call a method on a constructor
        textView.text = Datasource().loadAffirmations().size.toString()
    }
}