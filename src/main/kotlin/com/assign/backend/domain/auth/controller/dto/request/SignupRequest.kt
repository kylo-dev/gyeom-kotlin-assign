package com.assign.backend.domain.auth.controller.dto.request

import com.assign.backend.domain.auth.application.dto.request.SignupCommand
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignupRequest(
    @field:Email(message = "이메일 형식이 올바르지 않습니다.")
    val email: String,
    @field:NotBlank(message = "비밀번호는 필수입니다.")
    val password: String,
    val name: String,
) {
    fun toServiceRequest(): SignupCommand {
        return SignupCommand(
            email = email,
            password = password,
            name = name,
        )
    }
}
