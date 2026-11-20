package com.example.activity7_roomdatabase.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao {
    @Query("SELECT * FROM tblsiswa ORDER BY id ASC")
    fun getAllSiswaStream(): Flow<List<Siswa>>

    @Insert
    suspend fun insert(siswa: Siswa)


    //Edit : tambahkan 3 fungsi berikut
    @Query("SELECT * FROM tblSiswa WHERE id = :id")
    fun getSiswa(id: Int): Flow<Siswa>

    @Delete
    suspend fun delete(siswa: Siswa)



}