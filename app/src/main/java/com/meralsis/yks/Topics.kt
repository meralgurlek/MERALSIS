package com.meralsis.yks

data class Topic(val name: String, val subtopics: List<String> = emptyList())
data class Subject(val name: String, val topics: List<Topic>)
data class ExamSection(val name: String, val subjects: List<Subject>)

object YksCurriculum {
    val tyt = listOf(
        Subject("Türkçe", listOf(
            Topic("Sözcükte Anlam"), Topic("Cümlede Anlam"),
            Topic("Paragraf", listOf("Ana Düşünce", "Yardımcı Düşünce", "Yapı")),
            Topic("Dil Bilgisi", listOf("Sözcük Türleri", "Cümle Ögeleri", "Çatılar")),
            Topic("Ses Bilgisi"), Topic("Yazım Kuralları"), Topic("Noktalama İşaretleri"), Topic("Anlatım Bozuklukları")
        )),
        Subject("Matematik", listOf(
            Topic("Temel Kavramlar & Sayı Basamakları"), Topic("Bölme - Bölünebilme, EBOB-EKOK"),
            Topic("Rasyonel Sayılar & Basit Eşitsizlikler"), Topic("Mutlak Değer"), Topic("Üslü ve Köklü İfadeler"),
            Topic("Çarpanlara Ayırma"), Topic("Oran-Orantı"), Topic("Denklem Çözme"),
            Topic("Problemler", listOf("Sayı", "Kesir", "Yaş", "İşçi", "Kar-Zarar", "Karışım", "Hız")),
            Topic("Kümeler & Fonksiyonlar"), Topic("Permütasyon, Kombinasyon, Binom, Olasılık"), Topic("İstatistik")
        )),
        Subject("Geometri", listOf(
            Topic("Doğruda ve Üçgende Açılar"), Topic("Özel Üçgenler"), Topic("Çokgenler"), Topic("Dörtgenler"),
            Topic("Çember ve Daire"), Topic("Katı Cisimler"), Topic("Analitik Geometri")
        )),
        Subject("Fizik", listOf(
            Topic("Fizik Bilimine Giriş"), Topic("Madde ve Özellikleri"), Topic("Basınç ve Kaldırma Kuvveti"),
            Topic("Isı, Sıcaklık ve Genleşme"), Topic("Hareket ve Kuvvet"), Topic("Enerji"), Topic("Elektrostatik"),
            Topic("Elektrik ve Manyetizma"), Topic("Dalgalar"), Topic("Optik")
        )),
        Subject("Kimya", listOf(
            Topic("Kimya Bilimi"), Topic("Atom ve Periyodik Sistem"), Topic("Kimyasal Türler Arası Etkileşimler"),
            Topic("Maddenin Halleri"), Topic("Kimyanın Temel Kanunları ve Hesaplamalar"), Topic("Karışımlar"),
            Topic("Asitler, Bazlar ve Tuzlar"), Topic("Kimya Her Yerde")
        )),
        Subject("Biyoloji", listOf(
            Topic("Canlıların Ortak Özellikleri"), Topic("Canlıların Temel Bileşenleri"), Topic("Hücre ve Yapısı"),
            Topic("Canlıların Sınıflandırılması"), Topic("Hücre Bölünmeleri ve Üreme"), Topic("Kalıtım (Genetik)"),
            Topic("Ekosistem Ekolojisi ve Güncel Çevre Sorunları")
        )),
        Subject("Tarih", listOf(
            Topic("Tarih Bilimine Giriş"), Topic("İlk ve Orta Çağlarda Türk Dünyası"),
            Topic("İslam Medeniyetinin Doğuşu ve İlk Türk İslam Devletleri"),
            Topic("Beylikten Devlete Osmanlı Siyaseti ve Dünya Gücü Osmanlı"), Topic("Atatürkçülük ve İnkılap Tarihi")
        )),
        Subject("Coğrafya", listOf(
            Topic("Doğa ve İnsan"), Topic("Dünya'nın Şekli ve Hareketleri"), Topic("Coğrafi Konum ve Harita Bilgisi"),
            Topic("Atmosfer ve İklim Bilgisi"), Topic("Yeryüzünün Şekillenmesi (İç ve Dış Kuvvetler)"),
            Topic("Beşeri Yapı (Nüfus ve Göç)"), Topic("Bölgeler ve Uluslararası Ulaşım Hatları"), Topic("Doğal Afetler")
        )),
        Subject("Felsefe", listOf(
            Topic("Felsefeyi Tanıma ve Felsefi Düşünce"),
            Topic("Felsefenin Temel Soru ve Problemleri", listOf("Bilgi", "Varlık", "Ahlak", "Din", "Siyaset", "Bilim Felsefesi"))
        )),
        Subject("Din Kültürü", listOf(
            Topic("Bilgi ve İnanç / İslam ve İbadet"), Topic("Ahlak ve Değerler / Hz. Muhammed'in Hayatı"), Topic("Vahiy ve Akıl")
        ))
    )

    val ayt = listOf(
        Subject("Matematik", listOf(
            Topic("İleri Derece Fonksiyonlar"), Topic("Polinomlar & İkinci Dereceden Denklemler"), Topic("Eşitsizlikler"),
            Topic("Logaritma"), Topic("Diziler"), Topic("Trigonometri"), Topic("Limit ve Süreklilik"),
            Topic("Türev", listOf("Kurallar", "Uygulamaları")),
            Topic("İntegral", listOf("Belirli İntegral", "Belirsiz İntegral", "Alan Hesabı"))
        )),
        Subject("Geometri", listOf(Topic("Çemberin Analitik İncelenmesi"), Topic("Dönüşüm Geometrisi"), Topic("Katı Cisimler"))),
        Subject("Fizik", listOf(
            Topic("Vektörler, Kuvvet, Tork ve Denge"), Topic("Kütle Merkezi ve Basit Makineler"),
            Topic("Bir Boyutta ve İki Boyutta Sabit İvmeli Hareket"), Topic("İtme ve Çizgisel Momentum"),
            Topic("Düzgün Çembersel Hareket & Dönme Hareketi"), Topic("Basit Harmonik Hareket"), Topic("Radyoaktivite ve Modern Fizik")
        )),
        Subject("Kimya", listOf(
            Topic("Modern Atom Teorisi"), Topic("Gazlar"), Topic("Sıvı Çözeltiler ve Çözünürlük"),
            Topic("Kimyasal Tepkimelerde Enerji ve Hız"), Topic("Kimyasal Tepkimelerde Denge", listOf("Asit-Baz", "Çözünürlük Dengesi")),
            Topic("Kimya ve Elektrik"), Topic("Karbon Kimyasına Giriş & Organik Kimya")
        )),
        Subject("Biyoloji", listOf(
            Topic("İnsan Fizyolojisi (Sistemler)", listOf("Sinir", "Endokrin", "Duyu", "Destek-Hareket", "Sindirim", "Dolaşım", "Bağışıklık", "Solunum", "Boşaltım", "Üreme")),
            Topic("Komünite ve Popülasyon Ekolojisi"), Topic("Genden Proteine", listOf("DNA-RNA", "Protein Sentezi")),
            Topic("Canlılarda Enerji Dönüşümleri", listOf("Fotosentez", "Kemosentez", "Hücresel Solunum")), Topic("Bitki Biyolojisi")
        ))
    )
}
