package com.superyao.taipeizoo.ui.main.pavilion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superyao.taipeizoo.data.DataRepository
import com.superyao.taipeizoo.data.model.Pavilion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PavilionViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    val pavilions = MutableLiveData<List<Pavilion>>()

    fun loadPavilions(query: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.loadPavilions(query)
            pavilions.postValue(data)
        }
    }
}