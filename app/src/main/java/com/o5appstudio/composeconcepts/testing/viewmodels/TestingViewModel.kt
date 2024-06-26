package com.o5appstudio.composeconcepts.testing.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o5appstudio.composeconcepts.testing.SharedData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestingViewModel @Inject constructor() : ViewModel() {

    private val _sharedInt = MutableLiveData<Int>(0)
    val sharedInt: LiveData<Int> get() = _sharedInt

    fun updateValue(newValue: Int) {
        _sharedInt.value = newValue
    }

    private val _numList = MutableStateFlow<List<Int>>(SharedData.numberList)
    val numList:StateFlow<List<Int>>
        get() = _numList

    fun updateList(num : Int = 1){
        val currentList  = _numList.value.toMutableList()
        currentList.add(num+1)
        SharedData.numberList = currentList
        _numList.value = SharedData.numberList

    }


}