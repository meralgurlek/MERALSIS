package com.meralsis.yks

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

/** Lightweight local persistence without requiring a database dependency. */
class AppStore(context: Context) {
    private val prefs = context.getSharedPreferences("meralsis", Context.MODE_PRIVATE)

    fun setTopicDone(exam: String, subject: String, topic: String, done: Boolean) {
        prefs.edit().putBoolean("topic:$exam:$subject:$topic", done).apply()
    }
    fun isTopicDone(exam: String, subject: String, topic: String): Boolean =
        prefs.getBoolean("topic:$exam:$subject:$topic", false)

    fun saveStudySession(session: StudySession) {
        val array = JSONArray(prefs.getString("sessions", "[]"))
        array.put(JSONObject().apply {
            put("minutes", session.minutes); put("questions", session.questions)
            put("correct", session.correct); put("wrong", session.wrong); put("blank", session.blank)
        })
        prefs.edit().putString("sessions", array.toString()).apply()
    }

    fun loadStudySessions(): List<StudySession> {
        val array = JSONArray(prefs.getString("sessions", "[]"))
        return buildList {
            for (i in 0 until array.length()) {
                val o = array.getJSONObject(i)
                add(StudySession(o.getInt("minutes"), o.getInt("questions"), o.getInt("correct"), o.getInt("wrong"), o.getInt("blank")))
            }
        }
    }

    fun saveMistake(record: MistakeRecord) {
        val array = JSONArray(prefs.getString("mistakes", "[]"))
        array.put(JSONObject().apply {
            put("id", record.id); put("subject", record.subject); put("topic", record.topic)
            put("errorType", record.errorType.name); put("createdAt", record.createdAtMillis)
            put("repetition", record.repetition); put("ease", record.ease)
        })
        prefs.edit().putString("mistakes", array.toString()).apply()
    }
}
