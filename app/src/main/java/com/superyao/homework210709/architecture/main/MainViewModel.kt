package com.superyao.homework210709.architecture.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    val pavilions = MutableLiveData<List<Pavilion>>()

    fun refreshPavilions(query: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getPavilion(query)
            pavilions.postValue(data)
        }
    }
}