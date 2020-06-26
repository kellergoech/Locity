package de.zw.locity

import androidx.lifecycle.ViewModel
import de.zw.locity.actions.ParseDataRepository


class MainViewModel() : ViewModel(){
    private val TAG: String? = MainViewModel::class.simpleName

    fun addNewClasstoDatabse() {
       ParseDataRepository.writeArticle()
    }
}