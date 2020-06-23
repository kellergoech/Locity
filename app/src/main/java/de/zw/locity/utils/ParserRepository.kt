package de.zw.locity.utils

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import timber.log.Timber
import de.zw.locity.MainViewModel

class ParserRepository {

    private val TAG: String? = ParserRepository::class.simpleName


    fun <T> querryArticles(vararg channels: T): List<ParseDataType.Article> {
        var result = ArrayList<ParseDataType.Article>()
        val parseQuery = ParseQuery<ParseObject>("Post")

        parseQuery.findInBackground(FindCallback<ParseObject?> { scoreList, e ->
            if (e == null) {
                Timber.d("score Retrieved %s scores", scoreList.size)
                Log.d("Daten", scoreList.toString())
            } else {

                Timber.d("score Error: %s", e.message)
            }
        })
        return result
    }
}

