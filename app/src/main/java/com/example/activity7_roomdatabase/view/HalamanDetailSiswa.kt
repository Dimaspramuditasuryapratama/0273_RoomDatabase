package com.example.activity7_roomdatabase.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.activity7_roomdatabase.R
import com.example.activity7_roomdatabase.room.Siswa
import com.example.activity7_roomdatabase.uicontroller.DestinasiDetailSiswa
import com.example.activity7_roomdatabase.uicontroller.SiswaTopAppBar
import com.example.activity7_roomdatabase.viewmodel.DetailViewModel
import com.example.activity7_roomdatabase.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

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
        val coroutineScope = rememberCoroutineScope()

        // State untuk menampilkan dialog konfirmasi hapus
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            // Kartu Detail Siswa
            DetailSiswaCard(siswa = uiStateSiswa.detailSiswa)

            // Tombol Hapus
            OutlinedButton(
                onClick = { deleteConfirmationRequired = true },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.delete))
            }

            // Dialog Konfirmasi Hapus
            if (deleteConfirmationRequired) {
                AlertDialog(
                    onDismissRequest = { deleteConfirmationRequired = false },
                    title = { Text(stringResource(R.string.attention)) },
                    text = { Text(stringResource(R.string.tanya)) }, // Sesuai dengan strings.xml Anda
                    dismissButton = {
                        TextButton(onClick = { deleteConfirmationRequired = false }) {
                            Text(stringResource(R.string.no))
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            deleteConfirmationRequired = false
                            coroutineScope.launch {
                                viewModel.deleteSiswa()
                                navigateBack()
                            }
                        }) {
                            Text(stringResource(R.string.yes))
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DetailSiswaCard(
    siswa: Siswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            BarisDetailData(
                label = stringResource(R.string.nama1),
                value = siswa.nama
            )
            BarisDetailData(
                label = stringResource(R.string.alamat1),
                value = siswa.alamat
            )
            BarisDetailData(
                label = stringResource(R.string.telpon1),
                value = siswa.telpon
            )
        }
    }
}

@Composable
private fun BarisDetailData(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = value, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
    }
}