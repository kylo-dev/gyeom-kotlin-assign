package com.assign.backend.domain.auth.controller.dto.request

import com.assign.backend.domain.auth.application.dto.request.LoginCommand
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:Email(message = "이메일 형식이 올바르지 않습니다.")
    val email: String,
    @field:NotBlank(message = "비밀번호는 필수입니다.")
    val password: String,
) {
    fun toServiceRequest(): LoginCommand {
        return LoginCommand(
            email = email,
            password = password,
        )
    }
}
