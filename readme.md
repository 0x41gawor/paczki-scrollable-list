# Adding a RecyclerView to your app

There are a number of pieces involved in creating and using a `RecyclerView`. You can think of them as a division of labor. The diagram below shows an overview, and you will learn more about each piece as you implement it.

![](img/2.png)

## 1 Implement an Adapter for the RecyclerView

Your app needs a way to take the data from `Datasource`, and format it so that each `Affirmation` can be displayed as an item in the `RecyclerView`.

*Adapter* is a design pattern that adapts the data into something that can be used by `RecyclerView`. In this case, you need an adapter that takes an `Affirmation` instance from the list returned by `loadAffirmations()`, and turns it into a list item view, so that it can be displayed in the `RecyclerView.`

When you run the app, `RecyclerView` uses the adapter to figure out how to display your data on screen. `RecyclerView` asks the adapter to create a new list item view for the first data item in your list. Once it has the view, it asks the adapter to provide the data to draw the item. This process repeats until the `RecyclerView` doesn't need any more views to fill the screen. If only 3 list item views fit on the screen at once, the `RecyclerView` only asks the adapter to prepare those 3 list item views (instead of all 10 list item views).

### 1.1 Create a layout for items

Each item in the `RecyclerView` has its own layout, which you define in a separate layout file. Since you are only going to display a string, you can use a `TextView` for your item layout.

### 1.2 Create an ItemAdapter class

```kotlin
class ItemAdapter(private val context: Context, private val dataset: List<Affirmation>) {

}
```

The `ItemAdapter` needs information on how to resolve the string resources. This, and other information about the app, is stored in a `Context` object instance that you can pass into an `ItemAdapter` instance.

### 1.3 Create a ViewHolder

`RecyclerView` doesn't interact directly with item views, but deals with `ViewHolders` instead.

A `ViewHolder` represents a single list item view in `RecyclerView`, and can be reused when possible. 

A `ViewHolder` instance holds references to the individual views within a list item layout (hence the name "view holder").

This makes it easier to update the list item view with new data. 

View holders also add information that `RecyclerView` uses to efficiently move views around the screen.

You can create`ItemViewHolder` as a nested class in `ItemAdapter`

```kotlin
class ItemAdapter(private val context: Context, private val dataset: List<Affirmation>) {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
    }
}
```

### 1.4 Override adapter methods

Add the code to extend your `ItemAdapter` from the abstract class `RecyclerView.Adapter`. Specify `ItemAdapter.ItemViewHolder` as the view holder type in angle brackets.

```kotlin
class ItemAdapter(private val context: Context, private val dataset: List<Affirmation>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
    }
}
```

#### 1.4.1 Implement getItemCount()

The `getItemCount()` method needs to return the size of your dataset. Your app's data is in the `dataset` property that you are passing into the `ItemAdapter` constructor, and you can get its size with `size`.

#### 1.4.2 Implement onCreateViewHolder()

This method is called by the layout manager to create new view holders for the `RecyclerView` (when there are no existing view holders that can be reused). Remember that a view holder represents a single list item view.

The `onCreateViewHolder()` method takes two parameters and returns a new `ViewHolder`:

- A `parent` parameter, which is the view group that the new list item view will be attached to as a child. The parent param will be the `RecyclerView` defined in `activity_main` layout.
- A `viewType` parameter which becomes important when there are multiple item view types in the same `RecyclerView`. If you have different list item layouts displayed within the `RecyclerView`, there are different item view types. You can only recycle views with the same item view type. In your case, there is only one list item layout and one item view type, so you **don't** have to **worry about this parameter**.

Steps:

1. obtain an instance of `LayoutInflater` from the provided context (`context` of the `parent`).

   - The `LayoutInflater` knows how to inflate an XML layout into a hierarchy of view objects.

     - *inflate - ang.* - *increase (something) by a large or excessive amount. / fill (a balloon, tire, or other expandable structure) with air or gas so that it becomes distended.*

     - > ```kotlin
       > val layoutInflater = LayoutInflater.from(parent.context)
       > ```

2.  inflate the actual list item view.

   - Pass in the XML layout resource ID `R.layout.list_item` and the `parent` view group.

   - The third, boolean argument is `attachToRoot`. This argument needs to be `false`, because `RecyclerView` adds this item to the view hierarchy for you when it's time.

   - > ```kotlin
     >   val adapterLayout = layoutInflater.inflate(R.layout.list_item, parent, false)
     > ```

3. Now `adapterLayout` holds a reference to the list item view, so return a new `ItemViewHolder` instance where the root view is `adapterLayout`.

   - > ```kotlin
     > return ItemViewHolder(adapterLayout)
     > ```

#### 1.4.3 Implement onBindViewHolder()

This method is called by the layout manager in order to replace the contents of a list item view.

The `onBindViewHolder()` method has two parameters:

- `ItemViewHolder` previously created by the `onCreateViewHolder()` method and which needs the update (reusing the holder)
-  `int` that represents the current item `position` in the list. 

Steps:

1. obtain `item` with given `position` in `dataset`
2.  update `ViewHolder` with current (indicated by the `position`) data

```kotlin
override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    val item = dataset[position]
    holder.textView.text =  context.resources.getString(item.stringResourceId)
}
```

**!Note** that in Android Framework, you can call `getString(R.id)` and it will return a String value from `res/values/strings.xml` and this is method from `Recources` class which instance is a field in a `context` object.

### 1.4.4 Whole Code

```kotlin
package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Affirmation] data object.
 */
class ItemAdapter(
    private val context: Context,
    private val dataset: List<Affirmation>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size
}
```

## 2 Modify the MainActivity to use a RecyclerView

```kotlin
package com.example.affirmations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize data.
        val myDataset = Datasource().loadAffirmations()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }
}
```

## 3 Display a list of images

We simply:

- add `ImageResrouceId` to our data class.

- add `ImageView` to `list_item.xml` (and we contain it in `LinearLayout` along with existing here before `TextView`)
- add `ImageView` to our holder and set its view in `onBindViewHolder`

### 3.1 The whole code

#### #### ItemAdapter.kt

```kotlin
package pl.gawor.android.tutorials.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
```

