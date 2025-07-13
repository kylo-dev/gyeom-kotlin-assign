package com.assign.backend.domain.feedback.service

import com.assign.backend.domain.chat.domain.service.ChatService
import com.assign.backend.domain.feedback.domain.repository.FeedbackRepositoryPort
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.user.domain.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FeedbackService(
    private val repositoryPort: FeedbackRepositoryPort,
    private val userService: UserService,
    private val chatService: ChatService
) {
    @Transactional
    fun deleteFeedbacksByThreadId(threadId: ThreadId) {
        repositoryPort.deleteFeedbacksByThreadId(threadId)
    }


//    @Transactional
//    fun createFeedback(
//        userId: Long,
//        request: CreateFeedbackRequest
//    ) {
//        val chat = chatService.getChatById(request.chatId)
//        val user = userService.getUserById(userId)
//
//        // 권한 체크: 자신이 만든 대화만 피드백 가능 (관리자 제외)
//        if (chat.thread.user.id != userId) {
//            throw CustomBadRequestException("해당 대화에 피드백을 작성할 수 없습니다.")
//        }
//
//        // 이미 피드백 있는지 확인
//        checkDuplicateFeedback(chat, user)
//        val feedback = saveFeedback(chat, user, request.positive)
//    }
//
//    private fun checkDuplicateFeedback(
//        chat: ChatEntity,
//        user: UserEntity
//    ) {
//        if (feedbackJpaRepository.findByChatIdAndUserId(chat.id, user.id) != null) {
//            throw CustomBadRequestException("이미 해당 대화에 피드백을 작성했습니다.")
//        }
//    }
//
//    private fun saveFeedback(
//        chat: ChatEntity,
//        user: UserEntity,
//        positive: Boolean
//    ): FeedbackEntity {
//        return feedbackJpaRepository.save(
//            FeedbackEntity(chat = chat, user = user, positive = positive)
//        )
//    }
//
//    fun getFeedbackList(
//        userId: Long,
//        positive: Boolean?,
//        pageable: Pageable
//    ): List<FeedbackResponse> {
//        return getFeedBacksById(userId, pageable)
//            .filter { positive == null || it.positive == positive }
//            .map { toResponse(it) }
//            .toList()
//    }
//
//    fun getFeedBacksById(userId: Long, pageable: Pageable): Page<FeedbackEntity> {
//        return feedbackJpaRepository.findByUserId(userId, pageable)
//    }
//
//    @Transactional
//    fun updateStatus(feedbackId: Long, request: UpdateFeedbackStatusRequest) {
//        val feedback = getFeedbackById(feedbackId)
//        println(request.positive)
//        feedback.changePositive(request.positive)
//    }
//
//    fun getFeedbackById(feedbackId: Long): FeedbackEntity {
//        return feedbackJpaRepository.findByIdOrNull(feedbackId)
//            ?: throw CustomNotFoundException("존재하지 않는 피드백입니다.")
//    }

}