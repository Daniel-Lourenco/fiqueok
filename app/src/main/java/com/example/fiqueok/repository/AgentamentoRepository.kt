package com.example.fiqueok.repository

import com.example.fiqueok.data.db.entity.AgendamentoEntity

interface AgendamentoRepository {
    suspend fun insertAgendamento(especialidade: String, data: String, horario: String): Long

    suspend fun updateAgendamento(id: Long, especialidade: String, data: String, horario: String)

    suspend fun deleteAgendamento(id: Long)

    suspend fun deleteAllAgendamento()

    suspend fun getAllAgendamento(): List<AgendamentoEntity>
}