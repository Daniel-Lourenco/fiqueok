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
        val converter = ConversorDeData()
        val dataConvertida = converter.toOffsetDateTime(data)
        val agendamento = AgendamentoEntity(
            especialidade = especialidade,
            data = dataConvertida,
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
        val converter = ConversorDeData()
        val dataConvertida = converter.toOffsetDateTime(data)
        val agendamento = AgendamentoEntity(
            id = id,
            especialidade = especialidade,
            data = dataConvertida,
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