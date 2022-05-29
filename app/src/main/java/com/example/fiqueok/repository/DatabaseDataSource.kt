package com.example.fiqueok.repository

import com.example.fiqueok.data.db.dao.AtendimentosDAO
import com.example.fiqueok.data.db.entity.AgendamentoEntity
import com.example.fiqueok.ui.agendamento.ConversorDeData

class DatabaseDataSource(
    private val atendimentosDAO: AtendimentosDAO
) : AgendamentoRepository {

    override suspend fun insertAgendamento(
        especialidade: String,
        data: String,
        horario: String
    ): Long {
        val agendamento = AgendamentoEntity(
            especialidade = especialidade,
            data = data,
            horario = horario
            )
        return atendimentosDAO.insert(agendamento)
    }

    override suspend fun updateAgendamento(
        id: Long,
        especialidade: String,
        data: String,
        horario: String
    ) {
        val agendamento = AgendamentoEntity(
            id = id,
            especialidade = especialidade,
            data = data,
            horario = horario
        )
        atendimentosDAO.update(agendamento)
    }

    override suspend fun deleteAgendamento(id: Long) {
        atendimentosDAO.delete(id)
    }

    override suspend fun deleteAllAgendamento() {
        atendimentosDAO.deleteAll()
    }

    override suspend fun getAllAgendamento(): List<AgendamentoEntity> {
        return atendimentosDAO.getAll()
    }

}