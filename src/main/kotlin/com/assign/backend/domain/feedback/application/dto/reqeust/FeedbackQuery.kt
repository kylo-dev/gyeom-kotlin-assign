package com.assign.backend.domain.feedback.application.dto.reqeust

import com.assign.backend.domain.user.domain.model.User
import org.springframework.data.domain.Pageable

data class FeedbackQuery(
    val user: User,
    val positive: Boolean?,
    val pageable: Pageable,
) {
    companion object {
        fun of(user: User, positive: Boolean?, pageable: Pageable): FeedbackQuery {
            return FeedbackQuery(
                user,
                positive,
                pageable,
            )
        }
    }
}
