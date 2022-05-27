package com.example.fiqueok.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "atendimentos")
data class AgendamentoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val especialidade: String,
    val data: String,
    val horario: String
) : Parcelable