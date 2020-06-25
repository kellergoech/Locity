package de.zw.locity

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parse.FindCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import de.zw.locity.actions.ParseDataRepository
import de.zw.locity.datatypes.Article
import timber.log.Timber

class MainViewModel() : ViewModel(){
    //data class
    private val TAG: String? = MainViewModel::class.simpleName
    private val articlelist: MutableLiveData<Array<Article>> = MutableLiveData()

    fun getArticles() : LiveData<Array<Article>>{
        return articlelist
    }

    fun addNewClasstoDatabse() {
       ParseDataRepository.writeArticle()
    }
}