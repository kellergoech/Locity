package de.zw.locity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.zw.locity.utils.ParseDataType

class MainViewModel : ViewModel(){
    //data class
    

    private val _selectedID = MutableLiveData<Int>()
    val selectedID: LiveData<Int> = _selectedID

    init {
        _selectedID.value = 1
    }

    fun addNewClasstoDatabse(){
        ParseDataType.addNewClasstoDatabse()
    }

    fun searchDatabase(){

    }

    fun toggle(optionID : Int){
        _selectedID.value = optionID
    }
}