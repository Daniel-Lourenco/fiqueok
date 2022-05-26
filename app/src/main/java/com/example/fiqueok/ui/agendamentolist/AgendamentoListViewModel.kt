package com.example.fiqueok.ui.agendamentolist

import androidx.lifecycle.ViewModel
import com.example.fiqueok.repository.AgendamentoRepository

class AgendamentoListViewModel(
    private val repository: AgendamentoRepository
) : ViewModel() {
    val allAgendamentosEvent = repository.getAllAgendamento()
}