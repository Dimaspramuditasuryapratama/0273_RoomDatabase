package com.example.activity7_roomdatabase.room

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao {
    @Query("SELECT * FROM siswa ORDER BY id ASC")
    fun getAllSiswaStream(): Flow<List<Siswa>>

}