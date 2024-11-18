package com.example.frontenddd.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontenddd.data.DummyData
import com.example.frontenddd.Model.Resep
import com.example.frontenddd.R

@Composable
fun GridItem(
    resep: Resep,
    onClick: () -> Unit, // Tambahkan parameter untuk aksi klik
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        resep.foto?.let { painterResource(id = it) }?.let { painter ->
            Image(
                painter = painter,
                contentDescription = resep.nama,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(150.dp)
                    .padding(bottom = 8.dp)
            )
        }
        Text(
            text = resep.nama,
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(150.dp),
            maxLines = 2
        )
        Text(
            text = "Bahan: ${resep.bahan}",
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier.width(150.dp),
            maxLines = 2
        )
    }
}

@Composable
fun GridScreen(
    navController: NavController,
) {
    Column {
        Text(
            text = "Resep Favorit",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.padding(8.dp)
        )

        val resepList = DummyData.Resep

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(resepList) { resepItem ->
                GridItem(
                    resep = resepItem,
                    onClick = {
                        // Navigasi ke detail resep
                        navController.navigate("detail_screen/${resepItem.id}")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewResepItem() {
    val dummyResep = Resep(
        id = 1,
        nama = "Nasi Goreng",
        bahan = "Nasi, Saus Tomat, Daging Ayam",
        langkah = "Masukkan bahan-bahan tersebut ke dalam wajan, masak hingga matang.",
        foto = R.drawable.nasi_goreng
    )

    GridItem(resep = dummyResep, onClick = {})
}
