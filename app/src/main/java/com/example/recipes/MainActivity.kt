package com.example.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipes.R.font.sriracha_reguler
import com.example.recipes.ui.theme.RecipesTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RecipeApp() {
    val srirachaFont = FontFamily(Font(sriracha_reguler)) // Font Family Sriracha

    Column {
        // Header
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "30 Days of Recipes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF4CAF50), // Warna latar belakang TopAppBar
                titleContentColor = Color.White, // Warna teks
                navigationIconContentColor = Color.White, // Warna ikon navigasi
                actionIconContentColor = Color.White // Warna ikon aksi
            )
        )


        // Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(recipeList.size) { index ->
                RecipeCard(
                    day = index + 1,
                    recipeName = recipeList[index].name,
                    description = recipeList[index].description,
                    imageRes = recipeList[index].imageRes,
                    fontFamily = srirachaFont
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun RecipeCard(day: Int, recipeName: String, description: String, imageRes: Int, fontFamily: FontFamily) {
    var isExpanded by remember { mutableStateOf(false) } // State untuk mengontrol ekspansi

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize(), // Animasi perubahan ukuran konten
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Day $day",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50), // Warna hijau untuk header
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = recipeName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Gambar yang dapat diklik
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable { isExpanded = !isExpanded }, // Toggle ekspansi saat diklik
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Deskripsi muncul jika isExpanded == true
            if (isExpanded) {
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = fontFamily
                )
            }
        }
    }
}

// Sample data
data class Recipe(val name: String, val description: String, val imageRes: Int)

val recipeList = listOf(
    Recipe(
        name = "Spaghetti Bolognese",
        description = "Nikmati pasta klasik dengan saus daging yang kaya rasa.",
        imageRes = R.drawable.spaghetti_bolognese
    ),
    Recipe(
        name = "Chicken Teriyaki",
        description = "Resep ayam dengan saus manis khas Jepang.",
        imageRes = R.drawable.chicken_teriyaki
    ),
    Recipe(
        name = "Caesar Salad",
        description = "Salad segar dengan saus Caesar dan crouton renyah.",
        imageRes = R.drawable.caesar_salad
    ),
    Recipe(
        name = "Beef Stroganoff",
        description = "Potongan daging sapi lembut yang dimasak dengan saus krim asam dan jamur, disajikan di atas nasi atau pasta untuk hidangan ala Rusia yang memanjakan lidah.",
        imageRes = R.drawable.beef_stroganoff
    ),
    Recipe(
        name = "Soto Ayam",
        description = "Hidangan sup ayam beraroma rempah, disajikan dengan irisan telur rebus, bihun, dan kerupuk, memberikan kehangatan di setiap suapan.",
        imageRes = R.drawable.soto_ayam
    ),
    Recipe(
        name = "Rendang",
        description = "Daging sapi yang dimasak dengan santan dan campuran rempah selama berjam-jam hingga meresap, menghasilkan cita rasa khas Minang yang mendalam.",
        imageRes = R.drawable.rendang
    ),
    Recipe(
        name = "Ayam Goreng Crispy",
        description = "Ayam renyah dengan lapisan tepung berbumbu yang gurih dan tekstur dalam yang tetap juicy, sempurna dinikmati dengan saus sambal.",
        imageRes = R.drawable.ayam_goreng_crispy
    ),
    Recipe(
        name = "Sup Tom Yam",
        description = "Sup asam pedas khas Thailand yang dipenuhi dengan udang, cumi, dan jamur, serta aroma serai dan daun jeruk yang segar.",
        imageRes = R.drawable.sup_tomyam
    ),
    Recipe(
        name = "Lasagna",
        description = "Lapisan pasta, saus daging, dan keju yang dipanggang hingga keemasan, menciptakan kombinasi tekstur lembut dan rasa kaya khas Italia.",
        imageRes = R.drawable.lasagna
    ),
    Recipe(
        name = "Curry Jepang",
        description = "Hidangan kari kental dengan daging sapi atau ayam dan potongan kentang serta wortel, disajikan dengan nasi putih pulen.",
        imageRes = R.drawable.curry_jepang
    ),
    Recipe(
        name = "Burger Homemade",
        description = "Roti bun lembut dengan daging sapi panggang, selada segar, tomat, keju, dan saus mayones, menciptakan burger lezat ala restoran.",
        imageRes = R.drawable.burger_homemade
    ),
    Recipe(
        name = "Pasta Carbonara",
        description = "Pasta creamy dengan saus telur, keju parmesan, dan bacon yang memberikan sensasi lembut dan gurih.",
        imageRes = R.drawable.pasta_carbonara
    ),
    Recipe(
        name = "Chicken Parmigiana",
        description = "Fillet ayam goreng renyah yang dilapisi saus tomat khas Italia dan lelehan keju mozzarella, dipanggang hingga keemasan untuk hidangan yang menggoda selera.",
        imageRes = R.drawable.chicken_parmigiana
    ),
    Recipe(
        name = "Ratatouille",
        description = "Hidangan khas Prancis berupa irisan tipis sayuran seperti zucchini, terong, dan tomat yang dipanggang dengan bumbu sederhana namun kaya rasa.",
        imageRes = R.drawable.ratatouille
    ),
    Recipe(
        name = "Udang Saus Padang",
        description = "Udang segar yang dimasak dengan saus pedas berbumbu khas Padang, cocok sebagai lauk atau camilan.",
        imageRes = R.drawable.udang_saus_padang
    ),
)