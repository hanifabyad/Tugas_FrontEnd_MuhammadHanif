package com.example.frontenddd.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontenddd.R
import com.example.frontenddd.data.DummyData
import com.example.frontenddd.Model.Profil
import com.example.frontenddd.presentation.component.AboutItem

@Composable
fun About (
    profil: Profil,
    navController: NavController
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxSize()
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Profil", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            AboutItem(profil = Profil(1, "Muhammad Hanif", "4342301038", "Politeknik Negeri Batam", "Teknik Informatika", R.drawable.hanif))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Box(
                modifier = Modifier
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(text = "Name:", fontWeight = FontWeight.Bold)
                    Text(text = profil.nama, color = Color.Gray)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Menampilkan Umur
                    Text(text = "Asal:", fontWeight = FontWeight.Bold)
                    Text(text = profil.nim, color = Color.Gray)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Menampilkan Alamat
                    Text(text = "Jurusan:", fontWeight = FontWeight.Bold)
                    Text(text = profil.kampus, color = Color.Gray)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Menampilkan Email
                    Text(text = "Email:", fontWeight = FontWeight.Bold)
                    Text(text = profil.jurusan, color = Color.Gray)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAboutScreen () {
    About(
        profil = Profil(1, "Muhammad Hanif", "4342301038", "Politeknik Negeri Batam", "Teknik Informatika", R.drawable.hanif),
        navController = rememberNavController()
    )
}