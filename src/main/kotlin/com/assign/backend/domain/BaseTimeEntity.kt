package com.assign.backend.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

abstract class BaseTimeEntity {
    @CreatedDate
    var createdAt: LocalDateTime? = null
        protected set

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
        protected set
}