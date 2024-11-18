package com.example.frontenddd.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontenddd.Model.Resep
import com.example.frontenddd.R


@Composable
fun ResepItem (
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
                contentDescription = resep.nama,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(150.dp)
            )
        } ?: run {
            Text("Gambar tidak tersedia")
        }

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

//@Preview(showBackground = true)
//@Composable
//fun PreviewResepItem() {
//    ResepItem(
//        resep = Resep(
//            1,
//            "Nasi Goreng",
//            "Masukkan bahan-bahan tersebut ke dalam wajan, masak hingga matang",
//            R.drawable.nasi_goreng
//        )
//    ) { }
//}


