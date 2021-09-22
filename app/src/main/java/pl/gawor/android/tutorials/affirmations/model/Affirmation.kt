package pl.gawor.android.tutorials.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val stringResId: Int,
    @DrawableRes val imageResId: Int)

/*
Both stringResourceId and imageResourceId are integer values.
Although this looks okay, the caller could accidentally pass in the arguments in the wrong order (imageResourceId first instead of stringResourceId).

To avoid this, you can use Resource annotations. Annotations are useful because they add additional info to classes, methods, or parameters.

Then you will get a warning if you supply the wrong type of resource ID.
 */