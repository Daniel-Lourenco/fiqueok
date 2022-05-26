package com.example.fiqueok.ui.agendamento

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiqueok.R
import com.example.fiqueok.repository.AgendamentoRepository
import kotlinx.coroutines.launch

class AgendamentoViewModel(
    private val repository: AgendamentoRepository
) : ViewModel() {

    private val _agendamentoStateEventData = MutableLiveData<AgendamentoState>()
    val agendamentoStateEventData: LiveData<AgendamentoState>
        get() = _agendamentoStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addAgendamento(especialidade: String, data: String, horario: String) = viewModelScope.launch {
        try {
            val id = repository.insertAgendamento(especialidade, data, horario)
            if (id > 0){
                _agendamentoStateEventData.value = AgendamentoState.Inserted
                _messageEventData.value = R.string.agendamento_inserted_successfully
            }
        } catch (ex: Exception){
            _messageEventData.value = R.string.agendamento_error_to_insert
            Log.e(TAG, ex.toString())
        }
    }

    sealed class AgendamentoState {
        object Inserted : AgendamentoState()
    }

    companion object{
        private val TAG = AgendamentoViewModel::class.java.simpleName
    }
}