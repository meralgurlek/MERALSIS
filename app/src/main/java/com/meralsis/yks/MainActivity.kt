package com.meralsis.yks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { YksApp() }
    }
}

@Composable
fun YksApp() {
    MaterialTheme {
        var selected by remember { mutableIntStateOf(0) }
        val items = listOf("Ana Sayfa", "Dersler", "Denemeler", "Profil")
        Scaffold(bottomBar = {
            NavigationBar { items.forEachIndexed { i, title ->
                NavigationBarItem(selected = selected == i, onClick = { selected = i }, icon = {}, label = { Text(title) })
            }}
        }) { padding ->
            Column(Modifier.padding(padding).padding(20.dp)) {
                Text("MERALSİS YKS", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))
                when (selected) {
                    0 -> HomeScreen()
                    1 -> Text("TYT ve AYT dersleri")
                    2 -> Text("Deneme ve net takibi")
                    3 -> Text("Profil ve hedefler")
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(20.dp)) {
            Text("YKS Hedefin", style = MaterialTheme.typography.titleLarge)
            Text("Türkiye geneli ilk 100", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))
            Text("Çalışmalarını takip et, netlerini artır ve hedeflerine adım adım ilerle.")
        }
    }
    Spacer(Modifier.height(16.dp))
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        StatCard("TYT Net", "0")
        StatCard("AYT Net", "0")
    }
}

@Composable
fun RowScope.StatCard(title: String, value: String) {
    Card(Modifier.weight(1f)) { Column(Modifier.padding(16.dp)) { Text(title); Text(value, style = MaterialTheme.typography.headlineMedium) } }
}
