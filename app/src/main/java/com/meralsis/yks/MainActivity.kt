package com.meralsis.yks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { YksApp() } }
}

@Composable
fun YksApp() {
    MaterialTheme {
        var selected by remember { mutableIntStateOf(0) }
        val items = listOf("Ana Sayfa", "Dersler", "Denemeler", "Profil")
        Scaffold(bottomBar = { NavigationBar { items.forEachIndexed { i, title ->
            NavigationBarItem(selected == i, { selected = i }, icon = {}, label = { Text(title) })
        }}}) { padding -> Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
            when (selected) { 0 -> HomeScreen(); 1 -> CurriculumScreen(); 2 -> ExamScreen(); 3 -> ProfileScreen() }
        }}
    }
}

@Composable
fun HomeScreen() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { Text("MERALSİS YKS", style = MaterialTheme.typography.headlineMedium); Spacer(Modifier.height(8.dp));
            Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(20.dp)) {
                Text("YKS Hedefin", style = MaterialTheme.typography.titleLarge)
                Text("Türkiye geneli ilk 100", style = MaterialTheme.typography.headlineSmall)
                Text("Konularını tamamla, netlerini takip et ve hedefine ilerle.")
            }} }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) { StatCard("TYT Net", "0"); StatCard("AYT Net", "0") } }
        item { Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(20.dp)) {
            Text("Konu Takibi", style = MaterialTheme.typography.titleLarge)
            Text("TYT: ${YksCurriculum.tyt.sumOf { it.topics.size }} ana konu")
            Text("AYT: ${YksCurriculum.ayt.sumOf { it.topics.size }} ana konu")
        }}}
    }
}

@Composable
fun CurriculumScreen() {
    var exam by remember { mutableStateOf("TYT") }
    var expanded by remember { mutableStateOf<String?>(null) }
    val subjects = if (exam == "TYT") YksCurriculum.tyt else YksCurriculum.ayt
    Column(Modifier.fillMaxSize()) {
        Text("Konu Takibi", style = MaterialTheme.typography.headlineMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(exam == "TYT", { exam = "TYT" }, label = { Text("TYT") })
            FilterChip(exam == "AYT", { exam = "AYT" }, label = { Text("AYT") })
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(subjects) { subject -> Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) {
                Text(subject.name, style = MaterialTheme.typography.titleMedium)
                Text("${subject.topics.size} konu")
                TextButton({ expanded = if (expanded == subject.name) null else subject.name }) { Text(if (expanded == subject.name) "Gizle" else "Konuları göster") }
                if (expanded == subject.name) subject.topics.forEach { topic ->
                    var checked by remember { mutableStateOf(false) }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(topic.name, Modifier.weight(1f).padding(vertical = 5.dp)); Checkbox(checked, { checked = it })
                    }
                    if (topic.subtopics.isNotEmpty()) Text("Alt başlıklar: ${topic.subtopics.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
                }
            }}}
        }
    }
}

@Composable fun ExamScreen() { Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Text("Denemeler", style = MaterialTheme.typography.headlineMedium)
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(20.dp)) { Text("Deneme ve net takibi", style = MaterialTheme.typography.titleLarge); Text("TYT ve AYT denemelerini ve gelişimini kaydet.") } }
}}

@Composable fun ProfileScreen() { Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Text("Profil", style = MaterialTheme.typography.headlineMedium)
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(20.dp)) { Text("MERALSİS Öğrencisi", style = MaterialTheme.typography.titleLarge); Text("Hedef: Türkiye geneli ilk 100") } }
}}

@Composable fun RowScope.StatCard(title: String, value: String) { Card(Modifier.weight(1f)) { Column(Modifier.padding(16.dp)) { Text(title); Text(value, style = MaterialTheme.typography.headlineMedium) } } }
