package com.example.fiqueok.ui.agendamentolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiqueok.data.db.entity.AgendamentoEntity
import com.example.fiqueok.repository.AgendamentoRepository
import kotlinx.coroutines.launch

class AgendamentoListViewModel(
    private val repository: AgendamentoRepository
) : ViewModel() {

    private val _allAgendamentosEvent = MutableLiveData<List<AgendamentoEntity>>()
    val allAgendamentosEvent: LiveData<List<AgendamentoEntity>>
        get() = _allAgendamentosEvent

    fun getAgendamentos() = viewModelScope.launch {
        _allAgendamentosEvent.postValue(repository.getAllAgendamento())
    }
}