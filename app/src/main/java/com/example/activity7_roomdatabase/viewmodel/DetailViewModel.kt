package com.example.activity7_roomdatabase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activity7_roomdatabase.repositori.RepositoriSiswa
import com.example.activity7_roomdatabase.room.Siswa
import com.example.activity7_roomdatabase.uicontroller.DestinasiDetailSiswa
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getSiswaById()
    }

    private fun getSiswaById() {
        viewModelScope.launch {
            // Menggunakan fungsi yang benar dari Repositori Anda: getSiswaStream
            // .first() digunakan untuk mengambil nilai pertama (Siswa?) dari Flow
            val siswa = repositoriSiswa.getSiswaStream(idSiswa).first()

            if (siswa != null) {
                detailUiState = DetailUiState(detailSiswa = siswa)
            }
        }
    }

    fun deleteSiswa() {
        viewModelScope.launch {
            repositoriSiswa.deleteSiswa(detailUiState.detailSiswa)
        }
    }
}

data class DetailUiState(
    val detailSiswa: Siswa = Siswa(id = 0, nama = "", alamat = "", telpon = "")
)