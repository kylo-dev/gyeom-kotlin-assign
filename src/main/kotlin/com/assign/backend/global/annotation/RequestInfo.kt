package com.assign.backend.global.annotation

import com.assign.backend.domain.user.domain.model.User
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@Component
@RequestScope
class RequestInfo {
    lateinit var user: User

    fun setRequestMember(user: User) {
        this.user = user
    }
}