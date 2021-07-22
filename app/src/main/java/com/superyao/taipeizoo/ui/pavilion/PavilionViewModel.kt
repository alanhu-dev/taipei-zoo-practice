package com.superyao.taipeizoo.ui.pavilion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superyao.taipeizoo.data.DataRepository
import com.superyao.taipeizoo.data.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PavilionViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    val plants = MutableLiveData<List<Plant>>()

    fun loadPlants(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.loadPlants(query)
            plants.postValue(data)
        }
    }
}