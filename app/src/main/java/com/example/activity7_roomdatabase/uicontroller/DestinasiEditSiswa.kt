package com.example.activity7_roomdatabase.uicontroller

import com.example.activity7_roomdatabase.R

object DestinasiEditSiswa {
    val route = "item_edit"
    val titleRes = R.string.edit_siswa // Pastikan string "edit_siswa" ada di strings.xml
    val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}