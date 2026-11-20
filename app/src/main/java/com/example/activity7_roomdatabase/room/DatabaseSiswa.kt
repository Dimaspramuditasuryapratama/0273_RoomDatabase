package com.example.activity7_roomdatabase.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Siswa::class], version = 2, exportSchema = false) // <-- Ubah ke 2
abstract class DatabaseSiswa : RoomDatabase() {
    abstract fun siswaDao(): SiswaDao

    companion object {
        @Volatile
        private var Instance: DatabaseSiswa? = null

        fun getDatabase(context: Context): DatabaseSiswa {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseSiswa::class.java,
                    "siswa_database"
                )
                    .fallbackToDestructiveMigration() // <-- Tambahkan baris ini
                    .build()
                    .also { Instance = it }
            }
        }
    }
}