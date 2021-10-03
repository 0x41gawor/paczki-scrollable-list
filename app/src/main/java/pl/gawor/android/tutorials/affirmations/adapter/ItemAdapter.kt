package pl.gawor.android.tutorials.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.gawor.android.tutorials.affirmations.R
import pl.gawor.android.tutorials.affirmations.model.Affirmation


// Kotlin talks
// class A( // members specified in brackets (stands for default constructor) : extendedClass<Generic>() // constructor call in extends list
class ItemAdapter(private val dataset: List<Affirmation>, private val context: Context) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val checkBox: CheckBox = view.findViewById(R.id.item_checkBox)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        // create a new view
        val adapterLayout = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResId)
        holder.imageView.setImageResource(item.imageResId)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    // Kotlin talks
    // If Kotlin function returns single line u can simplify it
    override fun getItemCount() = dataset.size

}