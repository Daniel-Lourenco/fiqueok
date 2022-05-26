package com.example.fiqueok.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fiqueok.data.db.entity.AgendamentoEntity

@Dao
interface AtendimentosDAO {
    @Insert
    suspend fun insert(subscriber: AgendamentoEntity): Long

    @Update
    suspend fun update(subscriber: AgendamentoEntity)

    @Query("DELETE FROM atendimentos WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM atendimentos")
    suspend fun deleteAll()

    @Query("SELECT * FROM atendimentos")
    fun getAll(): LiveData<List<AgendamentoEntity>>
}