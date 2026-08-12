package com.meralsis.yks

import kotlin.math.pow

// Core domain models for MERALSIS. UI and backend services can build on these models.
data class StudySession(val minutes: Int, val questions: Int, val correct: Int, val wrong: Int, val blank: Int) {
    val net: Double get() = correct - wrong * 0.25
    val questionsPerHour: Double get() = if (minutes == 0) 0.0 else questions * 60.0 / minutes
    val netPerHour: Double get() = if (minutes == 0) 0.0 else net * 60.0 / minutes
}

enum class ErrorType { KNOWLEDGE, CONCEPT, CALCULATION, ATTENTION, TIME, READING, INDECISION }

data class MistakeRecord(
    val id: String,
    val subject: String,
    val topic: String,
    val errorType: ErrorType,
    val createdAtMillis: Long,
    val repetition: Int = 0,
    val ease: Double = 2.5
)

object SpacedRepetition {
    // Lightweight SM-2-inspired scheduler; persistent storage can later keep these values.
    fun nextIntervalDays(repetition: Int, ease: Double): Int {
        val base = when (repetition) { 0 -> 1; 1 -> 2; 2 -> 5; 3 -> 9; else -> (9 * (ease.pow(repetition - 3))).toInt() }
        return base.coerceIn(1, 60)
    }
}

data class Flashcard(val id: String, val front: String, val back: String, val topic: String, val dueAtMillis: Long)

data class WeeklyGoal(val title: String, val target: Int, val completed: Int = 0)

data class ProductivitySummary(val totalMinutes: Int, val totalQuestions: Int, val net: Double, val netPerHour: Double)

object ProductivityAnalyzer {
    fun summarize(sessions: List<StudySession>): ProductivitySummary {
        val minutes = sessions.sumOf { it.minutes }
        val questions = sessions.sumOf { it.questions }
        val net = sessions.sumOf { it.net }
        val nph = if (minutes == 0) 0.0 else net * 60.0 / minutes
        return ProductivitySummary(minutes, questions, net, nph)
    }
}
