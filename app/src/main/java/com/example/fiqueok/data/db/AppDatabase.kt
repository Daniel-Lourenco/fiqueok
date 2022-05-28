package com.example.fiqueok.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fiqueok.data.db.dao.AtendimentosDAO
import com.example.fiqueok.data.db.entity.AgendamentoEntity
import com.example.fiqueok.ui.agendamento.DateConverters

@Database(entities = [AgendamentoEntity::class], version = 1)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val atendimentosDAO: AtendimentosDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }

                return instance
            }
        }
    }
}