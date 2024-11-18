package com.example.frontenddd.presentation

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.frontenddd.Model.Resep
import com.example.frontenddd.data.DummyData
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontenddd.presentation.component.ResepItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.frontenddd.navigation.Screen
import com.example.frontenddd.presentation.component.ResepItem
import androidx.navigation.compose.rememberNavController


@Composable
fun PopularResepItem (
    resep: Resep,
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable {
            onItemClicked(resep.id)
        }
    ) {
        resep.foto?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = resep.nama, modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(150.dp)
            )
        }
        Text(
            text = resep.nama,
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(150.dp),
            maxLines = 1
        )
    }
}

@Composable
fun AllResepItem (
    resep: Resep,
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClicked(resep.id) }
    ) {
        resep.foto?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = resep.nama,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(80.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = resep.nama, style = MaterialTheme.typography.titleMedium)
            Row {
                Text(text = resep.bahan, color = MaterialTheme.colorScheme.primary, maxLines = 1)
            }
        }
    }
}

@Composable
fun HomeScreen (
    navController: NavController,
    modifier: Modifier = Modifier,
    resep: List<Resep> = DummyData.Resep
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        item {
            Column {
                Text(
                    text = "Resep Populer",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier
                ) {
                    items(resep, key = { it.id }) {
                        PopularResepItem(resep = it) { resepid ->
                            navController.navigate(Screen.DetailResep.route + "/$resepid")
                        }
                    }
                }
                Text(
                    text = "Resep Rekomendasi",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        items(resep, key = { it.id }) {
            AllResepItem(resep = it) { resepid ->
                navController.navigate(Screen.DetailResep.route + "/$resepid")
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//private fun PreviewListScreen (){
//    HomeScreen(navController = rememberNavController())
//}