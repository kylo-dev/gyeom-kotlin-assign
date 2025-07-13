package com.assign.backend.domain.feedback.domain.service

import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.service.ChatService
import com.assign.backend.domain.feedback.application.dto.reqeust.FeedbackQuery
import com.assign.backend.domain.feedback.application.dto.reqeust.UpdateFeedbackStatusCommand
import com.assign.backend.domain.feedback.domain.Feedback
import com.assign.backend.domain.feedback.domain.repository.FeedbackRepositoryPort
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.service.UserService
import com.assign.backend.global.exception.CustomBadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FeedbackService(
    private val repositoryPort: FeedbackRepositoryPort,
) {
    @Transactional
    fun deleteFeedbacksByThreadId(threadId: ThreadId) {
        repositoryPort.deleteFeedbacksByThreadId(threadId)
    }

    fun checkDuplicateFeedback(
        chat: Chat,
        user: User
    ) {
        if (repositoryPort.existsByChatIdAndUserId(chat.id, user.id)) {
            throw CustomBadRequestException("이미 해당 대화에 피드백을 작성했습니다.")
        }
    }

    fun saveFeedback(
        chat: Chat,
        user: User,
        positive: Boolean
    ) {
        return repositoryPort.save(chat = chat, user = user, positive = positive)
    }

    fun getFeedbackList(
        request: FeedbackQuery
    ): List<Feedback> {
        return repositoryPort.findAllByUserId(request.user.id, request.pageable)
            .filter { request.positive == null || it.positive == request.positive }
            .toList()
    }

    @Transactional
    fun updateStatus(request: UpdateFeedbackStatusCommand) {
//        val feedback = getFeedbackById(request.feedbackId)
//        feedback.changePositive(request.positive)
        repositoryPort.updateFeedbackPositive(request)
    }

}