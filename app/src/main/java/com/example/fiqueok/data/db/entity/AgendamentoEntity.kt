package com.example.fiqueok.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "atendimentos")
data class AgendamentoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val especialidade: String,
    val data: String,
    val horario: String
)