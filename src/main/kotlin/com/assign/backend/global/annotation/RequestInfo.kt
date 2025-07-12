package com.assign.backend.global.annotation

import com.assign.backend.domain.user.entity.UserEntity
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@Component
@RequestScope
class RequestInfo {
    lateinit var user: UserEntity

    fun setRequestMember(user: UserEntity) {
        this.user = user
    }
}