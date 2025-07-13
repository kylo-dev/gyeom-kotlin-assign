package com.assign.backend.domain.login_log.entity

import com.assign.backend.domain.common.BaseTimeEntity
import com.assign.backend.domain.user.entity.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "login_logs")
class LoginLogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: UserEntity,
) : BaseTimeEntity() {
}