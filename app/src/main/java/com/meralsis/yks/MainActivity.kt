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
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { YksApp() } }
}

@Composable
fun YksApp() {
    MaterialTheme {
        var selected by remember { mutableIntStateOf(0) }
        val pages = listOf("Ana Sayfa", "Dersler", "Çalışma", "Hata Defteri", "Plan", "Profil")
        Scaffold(bottomBar = { NavigationBar { pages.forEachIndexed { i, title ->
            NavigationBarItem(selected = selected == i, onClick = { selected = i }, icon = {}, label = { Text(title) })
        }}}) { padding ->
            Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
                when (selected) {
                    0 -> DashboardScreen()
                    1 -> CurriculumScreen()
                    2 -> FocusScreen()
                    3 -> MistakeScreen()
                    4 -> PlanScreen()
                    else -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    val left = YksDate.remaining()
    val days = TimeUnit.MILLISECONDS.toDays(left.toMillis())
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("MERALSİS", style = MaterialTheme.typography.headlineLarge)
            Text("Türkiye İlk 100 • Tıp Fakültesi", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp)) {
                Text("YKS'ye kalan", style = MaterialTheme.typography.titleMedium)
                Text("$days gün", style = MaterialTheme.typography.displaySmall)
                Text("Hedefinden vazgeçme. Bugünün çalışması yarının derecesidir.")
            }}
        }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MetricCard("TYT Net", "0"); MetricCard("AYT Net", "0"); MetricCard("Bugün", "0 dk")
        }}
        item { Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp)) {
            Text("Bugünün öncelikleri", style = MaterialTheme.typography.titleLarge)
            Text("• 80 AYT Matematik sorusu")
            Text("• 15 hata tekrarı")
            Text("• 2 odak oturumu")
        }}}
        item { Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp)) {
            Text("AI Koç", style = MaterialTheme.typography.titleLarge)
            Text("Performans verilerin biriktikçe MERALSİS zayıf konularını belirleyip haftalık öneriler oluşturacak.")
        }}}
    }
}

@Composable
fun CurriculumScreen() {
    var exam by remember { mutableStateOf("TYT") }
    var expanded by remember { mutableStateOf<String?>(null) }
    val subjects = if (exam == "TYT") YksCurriculum.tyt else YksCurriculum.ayt
    Column(Modifier.fillMaxSize()) {
        Text("Müfredat ve Konu Takibi", style = MaterialTheme.typography.headlineMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = exam == "TYT", onClick = { exam = "TYT" }, label = { Text("TYT") })
            FilterChip(selected = exam == "AYT", onClick = { exam = "AYT" }, label = { Text("AYT") })
        }
        Spacer(Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(subjects) { subject -> Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(14.dp)) {
                Text(subject.name, style = MaterialTheme.typography.titleMedium)
                Text("${subject.topics.size} konu")
                TextButton(onClick = { expanded = if (expanded == subject.name) null else subject.name }) { Text(if (expanded == subject.name) "Gizle" else "Konuları göster") }
                if (expanded == subject.name) subject.topics.forEach { topic ->
                    var checked by remember { mutableStateOf(false) }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(topic.name, Modifier.weight(1f).padding(vertical = 4.dp)); Checkbox(checked, { checked = it })
                    }
                }
            }}}
        }
    }
}

@Composable
fun FocusScreen() {
    var running by remember { mutableStateOf(false) }
    var minutes by remember { mutableIntStateOf(25) }
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Odak Modu", style = MaterialTheme.typography.headlineMedium)
        Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(24.dp)) {
            Text(if (running) "🌳 Ağaç büyüyor" else "🌱 Yeni fidan", style = MaterialTheme.typography.headlineMedium)
            Text("$minutes dakika", style = MaterialTheme.typography.displaySmall)
            Text("Çalışma sırasında uygulamadan çıkmadan odağını koru.")
            Button(onClick = { running = !running }) { Text(if (running) "Odağı Bitir" else "Çalışmaya Başla") }
        }}
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { minutes = 25 }) { Text("25 dk") }
            OutlinedButton(onClick = { minutes = 50 }) { Text("50 dk") }
            OutlinedButton(onClick = { minutes = 90 }) { Text("90 dk") }
        }
    }
}

@Composable
fun MistakeScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Hata Defteri", style = MaterialTheme.typography.headlineMedium)
        Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp)) {
            Text("Bugünkü tekrarlar", style = MaterialTheme.typography.titleLarge)
            Text("0 bekleyen hata")
            Text("Yanlış sorularını konu ve hata türüyle kaydet; aralıklı tekrar sistemi doğru zamanda yeniden karşına çıkarsın.")
            Button(onClick = {}) { Text("Hata Ekle") }
        }}
        Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp)) {
            Text("AI Soru Çöz", style = MaterialTheme.typography.titleLarge)
            Text("Soru fotoğrafını gönderdiğinde backend üzerinden AI çözüm, konu ve hata analizi yapılacak.")
            OutlinedButton(onClick = {}) { Text("Fotoğraf Seç") }
        }}
    }
}

@Composable
fun PlanScreen() {
    val goals = listOf("AYT Matematik • 80 soru", "TYT Türkçe • 40 paragraf", "15 hata tekrarı", "2 odak oturumu")
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item { Text("Haftalık Plan", style = MaterialTheme.typography.headlineMedium) }
        items(goals) { goal -> var done by remember { mutableStateOf(false) }; Card(Modifier.fillMaxWidth()) {
            Row(Modifier.padding(14.dp), horizontalArrangement = Arrangement.SpaceBetween) { Text(goal, Modifier.weight(1f)); Checkbox(done, { done = it }) }
        }}
        item { Button(onClick = {}) { Text("AI ile Haftalık Plan Oluştur") } }
    }
}

@Composable
fun ProfileScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Profil", style = MaterialTheme.typography.headlineMedium)
        Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp)) {
            Text("MERALSİS Öğrencisi", style = MaterialTheme.typography.titleLarge)
            Text("Alan: Sayısal")
            Text("Hedef: Türkiye İlk 100")
            Text("Hedef bölüm: Tıp Fakültesi")
        }}
    }
}

@Composable
fun RowScope.MetricCard(title: String, value: String) { Card(Modifier.weight(1f)) { Column(Modifier.padding(12.dp)) { Text(title); Text(value, style = MaterialTheme.typography.titleLarge) } } }
