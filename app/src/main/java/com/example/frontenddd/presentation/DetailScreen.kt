package com.example.frontenddd.presentation.DetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontenddd.R
import com.example.frontenddd.data.DummyData
import com.example.frontenddd.Model.Resep

@Composable
fun DetailResepContent(
    newResepList: List<Resep>,
    modifier: Modifier = Modifier
) {
    if (newResepList.isNotEmpty()) {
        val resep = newResepList[0]

        Column(modifier = modifier.padding(16.dp)) {
            // Row untuk gambar dan judul
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Gambar
                resep.foto?.let { painterResource(id = it) }?.let {
                    Image(
                        painter = it,
                        contentDescription = resep.nama,
                        modifier = Modifier
                            .size(height = 250.dp, width = 170.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                // Judul
                Text(
                    text = resep.nama,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Jarak antara Row dan bahan/langkah

            // Column untuk bahan dan langkah
            Column(modifier = Modifier.fillMaxWidth()) {
                // Bahan
                Text(
                    text = "Bahan:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = resep.bahan,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(16.dp)) // Jarak antara bahan dan langkah

                // Langkah
                Text(
                    text = "Langkah:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = resep.langkah,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    } else {
        Text(
            text = "Resep not found",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(16.dp)
        )
    }
}

@Composable
fun DetailResepScreen (
    modifier: Modifier = Modifier,
    navController: NavController,
    resepid: Int?
) {
    val newResepList = DummyData.Resep.filter { resep -> resep.id == resepid }
    Column(
        modifier = modifier
    ) {
        DetailResepContent(newResepList = newResepList, modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailContent () {
    val sampleResep = Resep(
        1,
        "Nasi Goreng",
        "Masukkan bahan-bahan tersebut ke dalam wajan, masak hingga matang",
         langkah = "Potong dan cincang bawang putih dan merah, tumis hingga harum , masukkan telur " +
                 "lalu masukkan nasi nya masak hingga matang",
         foto = R.drawable.nasi_goreng
    )
    DetailResepContent(
        newResepList = listOf(sampleResep),
        modifier = Modifier.fillMaxWidth()
    )
}

