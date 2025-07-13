package com.assign.backend.domain.chat.domain.service

import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.chat.domain.model.UserChat
import com.assign.backend.domain.chat.domain.repository.ChatRepositoryPort
import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.global.util.orNotFound
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ChatService(
    private val chatRepository: ChatRepositoryPort,
) {
    fun saveChat(thread: Thread, question: String, answer: String): Chat {
        return chatRepository.saveChat(thread, question, answer)
    }

    fun getChatById(chatId: Long): Chat {
        return chatRepository.findById(ChatId(chatId))
            .orNotFound("존재하지 않는 채팅입니다.")
    }

    fun countTodayChat(start: LocalDateTime, end: LocalDateTime): Int {
        return chatRepository.countByCreatedAtBetween(start, end)
    }

    fun getTodayChats(start: LocalDateTime, end: LocalDateTime): List<UserChat> {
        return chatRepository.findAllByCreatedAtBetween(start, end)
    }

    fun findTopNChatsByThreadIds(threadIds: List<Long>, limit: Int): List<Chat> {
        return chatRepository.findTopNChatsByThreadIds(threadIds)
    }
}