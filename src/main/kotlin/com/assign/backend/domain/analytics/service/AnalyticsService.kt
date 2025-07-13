package com.assign.backend.domain.analytics.service

import com.assign.backend.domain.analytics.controller.dto.response.ActivitySummaryResponse
import com.assign.backend.domain.chat.service.ChatService
import com.assign.backend.domain.login_log.repository.LoginLogJpaRepository
import com.assign.backend.domain.user.service.UserService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class AnalyticsService(
    private val userService: UserService,
    private val loginLogJpaRepository: LoginLogJpaRepository,
    private val chatService: ChatService,
) {

    fun getTodaySummary(): ActivitySummaryResponse {
        val (start, end) = todayRange()

        val signupCount = userService.countTodaySignup(start, end)
        val loginCount = loginLogJpaRepository.countByCreatedAtBetween(start, end)
        val chatCount = chatService.countTodayChat(start, end)

        return ActivitySummaryResponse(signupCount, loginCount, chatCount)
    }

    private fun todayRange(): Pair<LocalDateTime, LocalDateTime> {
        val now = LocalDateTime.now()
        val start = now.toLocalDate().atTime(LocalTime.MIN)
        val end = now.toLocalDate().atTime(LocalTime.MAX)
        return start to end
    }

    fun generateTodayCsv(): String {
        val (start, end) = todayRange()

        val todayChats = chatService.getTodayChats(start, end)

        val header = "chat_id,question,answer,created_at,user_email\n"
        val body = todayChats.joinToString("\n") {
            "${it.id},\"${it.question}\",\"${it.answer}\",${it.createdAt},${it.thread.user.email}"
        }
        return header + body
    }
}