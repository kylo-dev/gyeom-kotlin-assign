package com.assign.backend.domain.chat.domain.repository

import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.chat.domain.model.UserChat
import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

interface ChatRepositoryPort {
    fun findById(chatId: ChatId): Chat?
    fun findByThreadId(threadId: ThreadId, pageable: Pageable): Page<Chat>
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Int
    fun findAllByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): List<UserChat>
    fun deleteChatsByThreadId(threadId: ThreadId)
    fun saveChat(thread: Thread, question: String, answer: String): Chat
    fun findTopNChatsByThreadIds(threadIds: List<Long>): List<Chat>
}