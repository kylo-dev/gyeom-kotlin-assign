package com.assign.backend.domain.chat.infrastructure.repository

import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.chat.domain.repository.ChatRepositoryPort
import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class ChatRepository(
    private val repository: ChatJpaRepository,
) : ChatRepositoryPort {
    override fun findById(chatId: ChatId): Chat? {
        return repository.findByIdOrNull(chatId.value)
            ?.let { ChatMapper.toModel(it) }
    }

    override fun findByThreadId(threadId: ThreadId, pageable: Pageable): Page<Chat> {
        return repository.findByThreadId(threadId.value, pageable)
            .map { ChatMapper.toModel(it) }
    }

    override fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Int {
        return repository.countByCreatedAtBetween(start, end)
    }

    override fun findAllByCreatedAtBetween(
        start: LocalDateTime,
        end: LocalDateTime
    ): List<Chat> {
        return repository.findAllByCreatedAtBetween(start, end)
            .map { ChatMapper.toModel(it) }
    }

    override fun deleteChatsByThreadId(threadId: ThreadId) {
        repository.deleteChatsByThreadId(threadId.value)
    }

    override fun saveChat(thread: Thread, question: String, answer: String): Chat {
        val newChat = repository.save(ChatMapper.createChat(thread, question, answer))
        return ChatMapper.toModel(newChat)
    }

    override fun findTopNChatsByThreadIds(threadIds: List<Long>): List<Chat> {
        return repository.findTopNChatsByThreadIds(threadIds, 10)
            .map { ChatMapper.toModel(it) }
    }
}