package com.example.activity7_roomdatabase.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.activity7_roomdatabase.R
import com.example.activity7_roomdatabase.uicontroller.DestinasiDetailSiswa
import com.example.activity7_roomdatabase.uicontroller.SiswaTopAppBar
import com.example.activity7_roomdatabase.viewmodel.DetailViewModel
import com.example.activity7_roomdatabase.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiDetailSiswa.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Arahkan ke Edit jika mau, atau hapus FAB ini untuk sementara
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.update),
                )
            }
        }, modifier = modifier
    ) { innerPadding ->
        val uiStateSiswa = viewModel.detailUiState

        // Body Konten akan ditambahkan di commit berikutnya
        DetailSiswaContent(
            uiStateSiswa = uiStateSiswa,
            navigateBack = navigateBack,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}