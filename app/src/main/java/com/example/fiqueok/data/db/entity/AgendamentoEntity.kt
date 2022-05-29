package com.example.fiqueok.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.OffsetDateTime


@Parcelize
@Entity(tableName = "atendimentos")
data class AgendamentoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val especialidade: String,
    val data: String, //OffsetDateTime?= null,
    val horario: String
) : Parcelable