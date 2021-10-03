package pl.gawor.android.tutorials.affirmations.data

import pl.gawor.android.tutorials.affirmations.R
import pl.gawor.android.tutorials.affirmations.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.image1, false),
            Affirmation(R.string.affirmation2, R.drawable.image2, false),
            Affirmation(R.string.affirmation3, R.drawable.image3, false),
            Affirmation(R.string.affirmation4, R.drawable.image4, false),
            Affirmation(R.string.affirmation5, R.drawable.image5, false),
            Affirmation(R.string.affirmation6, R.drawable.image6,false),
            Affirmation(R.string.affirmation7, R.drawable.image7, false),
            Affirmation(R.string.affirmation8, R.drawable.image8, false),
            Affirmation(R.string.affirmation9, R.drawable.image9, false),
            Affirmation(R.string.affirmation10, R.drawable.image10, false)
        )
    }
}