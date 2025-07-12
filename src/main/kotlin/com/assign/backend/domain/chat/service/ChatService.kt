package com.assign.backend.domain.chat.service

import com.assign.backend.domain.chat.controller.dto.ChatResponse
import com.assign.backend.domain.chat.controller.dto.request.CreateChatRequest
import com.assign.backend.domain.chat.controller.dto.response.ThreadGroupResponse
import com.assign.backend.domain.chat.entity.ChatEntity
import com.assign.backend.domain.chat.repository.ChatMapper
import com.assign.backend.domain.chat.repository.ChatRepository
import com.assign.backend.domain.thread.entity.ThreadEntity
import com.assign.backend.domain.thread.repository.ThreadJpaRepository
import com.assign.backend.domain.thread.repository.ThreadMapper
import com.assign.backend.domain.user.service.UserService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val threadJpaRepository: ThreadJpaRepository,
    private val userService: UserService,
) {

    fun createChat(userId: Long, request: CreateChatRequest): ChatResponse {
        val loginUser = userService.getUserById(userId)
        val lastThread =
            threadJpaRepository.findFirstByUserIdOrderByCreatedAtDesc(userId)
        val now = LocalDateTime.now()

        val thread = if (isExpiredThread(lastThread, now)) {
            threadJpaRepository.save(ThreadMapper.createThread(loginUser))
        } else {
            lastThread
        }

        val answer = generateAnswer(request.question, request.model)
        val newChat = saveChat(thread!!, request.question, answer)
        return ChatResponse(newChat.id!!, newChat.question, newChat.answer, newChat.createdAt!!)
    }

    fun isExpiredThread(lastThread: ThreadEntity?, now: LocalDateTime): Boolean {
        return lastThread?.createdAt!!.plusMinutes(30).isBefore(now)
    }

    private fun generateAnswer(question: String, model: String?): String {
        // OpenAI API 연동
        return "답변 예시 (${question}에 대한 AI 응답)"
    }

    fun saveChat(thread: ThreadEntity, question: String, answer: String): ChatEntity {
        return chatRepository.save(
            ChatMapper.createChat(thread, question, answer)
        )
    }

    fun getUserChats(userId: Long, pageable: Pageable): List<ThreadGroupResponse> {
        val threads = threadJpaRepository.findAllByUserId(userId)
        return threads.map { thread ->
            val chats = chatRepository.findByThreadId(thread.id!!, pageable)
                .map { ChatResponse.of(it) }
                .toList()
            ThreadGroupResponse.of(thread.id!!, chats)
        }
    }
}