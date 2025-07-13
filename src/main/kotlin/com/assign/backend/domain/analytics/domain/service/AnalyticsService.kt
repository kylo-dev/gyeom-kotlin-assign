package com.assign.backend.domain.analytics.domain.service

import com.assign.backend.domain.analytics.application.dto.response.ActivitySummaryResult
import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.UserChat
import com.assign.backend.domain.chat.domain.service.ChatService
import com.assign.backend.domain.login_log.domain.service.LoginLogService
import com.assign.backend.domain.user.domain.service.UserService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class AnalyticsService(
    private val userService: UserService,
    private val loginLogService: LoginLogService,
    private val chatService: ChatService,
) {

    fun getTodaySummary(): ActivitySummaryResult {
        val (start, end) = todayRange()

        val signupCount = userService.countTodaySignup(start, end)
        val loginCount = loginLogService.countByCreatedAtBetween(start, end)
        val chatCount = chatService.countTodayChat(start, end)

        return ActivitySummaryResult(signupCount, loginCount, chatCount)
    }

    private fun todayRange(): Pair<LocalDateTime, LocalDateTime> {
        val now = LocalDateTime.now()
        val start = now.toLocalDate().atTime(LocalTime.MIN)
        val end = now.toLocalDate().atTime(LocalTime.MAX)
        return start to end
    }

    fun generateTodayCsv(): String {
        val (start, end) = todayRange()

        val todayChats: List<UserChat> = chatService.getTodayChats(start, end)

        val header = "chat_id,question,answer,created_at,user_email\n"
        val body = todayChats.joinToString("\n") {
            "${it.chat.chatId},\"${it.chat.question}\",\"${it.chat.answer}\",${it.chat.timestamps.createdAt},${it.user.emailValue}"
        }
        return header + body
    }
}