package com.assign.backend.domain.auth.controller

import com.assign.backend.domain.auth.service.AuthService
import com.assign.backend.domain.user.controller.dto.request.LoginRequest
import com.assign.backend.domain.user.controller.dto.request.SignupRequest
import com.assign.backend.global.jwt.TokenResponse
import com.assign.backend.global.UrlConstant
import com.assign.backend.global.response.ResponseData
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlConstant.AUTH)
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseData<String> {
        authService.signup(request)
        return ResponseData.success("회원가입에 성공했습니다.")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseData<TokenResponse> {
        val token = authService.login(request)
        return ResponseData.success(token)
    }
}