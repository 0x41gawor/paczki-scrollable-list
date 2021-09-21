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

