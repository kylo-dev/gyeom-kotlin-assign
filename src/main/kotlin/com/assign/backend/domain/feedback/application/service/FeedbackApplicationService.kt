package com.assign.backend.domain.feedback.application.service

import com.assign.backend.domain.chat.domain.service.ChatService
import com.assign.backend.domain.feedback.application.dto.reqeust.CreateFeedbackCommand
import com.assign.backend.domain.feedback.domain.service.FeedbackService
import com.assign.backend.global.exception.CustomBadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FeedbackApplicationService(
    private val chatService: ChatService,
    private val feedbackService: FeedbackService,
) {

    // TODO: 수정
    @Transactional
    fun createFeedback(
        request: CreateFeedbackCommand
    ) {
        val user = request.user
        val chat = chatService.getChatById(request.chatId)

        // 권한 체크: 자신이 만든 대화만 피드백 가능 (관리자 제외)
        if (chat.userIdValue != user.userId) {
            throw CustomBadRequestException("해당 대화에 피드백을 작성할 수 없습니다.")
        }

        // 이미 피드백 있는지 확인
        feedbackService.checkDuplicateFeedback(chat, user)
        feedbackService.saveFeedback(chat, user, request.positive)
    }

}