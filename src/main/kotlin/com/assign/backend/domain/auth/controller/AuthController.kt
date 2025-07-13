package com.assign.backend.domain.auth.controller

import com.assign.backend.domain.auth.application.service.AuthApplicationService
import com.assign.backend.domain.auth.controller.dto.request.LoginRequest
import com.assign.backend.domain.auth.controller.dto.request.SignupRequest
import com.assign.backend.global.util.UrlConstant
import com.assign.backend.global.jwt.TokenResponse
import com.assign.backend.global.response.ResponseData
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlConstant.AUTH)
class AuthController(
    private val authApplicationService: AuthApplicationService,
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignupRequest): ResponseData<String> {
        authApplicationService.signup(request.toServiceRequest())
        return ResponseData.success("회원가입에 성공했습니다.")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseData<TokenResponse> {
        val token = authApplicationService.login(request.toServiceRequest())
        return ResponseData.success(token.toResponse())
    }
}