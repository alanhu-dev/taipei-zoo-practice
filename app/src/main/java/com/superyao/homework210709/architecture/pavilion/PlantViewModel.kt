package com.superyao.homework210709.architecture.pavilion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    val plants = MutableLiveData<List<Plant>>()

    fun refreshPlants(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getPlant(query)
            plants.postValue(data)
        }
    }
}