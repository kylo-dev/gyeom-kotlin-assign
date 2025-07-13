package com.assign.backend.domain.chat.application.service

import com.assign.backend.domain.chat.application.dto.request.CreateChatCommand
import com.assign.backend.domain.chat.application.dto.response.ChatResult
import com.assign.backend.domain.chat.application.dto.response.ThreadGroupResult
import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.service.ChatService
import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.thread.domain.service.ThreadService
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ChatApplicationService(
    private val threadService: ThreadService,
    private val chatService: ChatService,
) {

    fun createChat(request: CreateChatCommand): ChatResult {
        val userId = request.user.id
        val lastThread = threadService.findLatestThread(userId)
        val now = LocalDateTime.now()

        val thread = lastThread
            ?.takeIf { !it.isExpiredThread(now) }
            ?: threadService.save(request.user)

        val answer = generateAnswer(request.question, request.model)
        val newChat = chatService.saveChat(thread, request.question, answer)
        return ChatResult.from(newChat)
    }

    private fun generateAnswer(question: String, model: String?): String {
        // OpenAI API 연동
        return "답변 예시 (${question}에 대한 AI 응답)"
    }

    // TODO: 쿼리 수정
    fun getUserChats(user: User, pageable: Pageable): List<ThreadGroupResult> {

        // 1. 사용자의 모든 스레드 조회
        val threads: List<Thread> =
            threadService.findAllByUserId(UserId(user.getUserId()), pageable)
        val threadIds = threads.map { it.getThreadId() }

        // 2. 스레드의 해당하는 모든 채팅 조회
        val chats = chatService.findTopNChatsByThreadIds(threadIds, 10)

        // 3. 스레드-채팅 그룹핑
        val chatsGroup: Map<ThreadId, List<Chat>> = chats.groupBy { it.thread.id }

        return threads.map { thread ->
            val groupChats = chatsGroup[thread.id].orEmpty()
                .map { ChatResult.from(it) }

            ThreadGroupResult(thread.id, groupChats)
        }
    }
}