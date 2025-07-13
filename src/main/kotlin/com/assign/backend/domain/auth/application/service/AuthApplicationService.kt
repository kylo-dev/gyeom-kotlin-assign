package com.assign.backend.domain.auth.application.service

import com.assign.backend.domain.auth.application.dto.request.LoginCommand
import com.assign.backend.domain.auth.application.dto.request.SignupCommand
import com.assign.backend.domain.login_log.entity.LoginLogEntity
import com.assign.backend.domain.login_log.infrastructure.repository.LoginLogJpaRepository
import com.assign.backend.domain.user.domain.model.Password
import com.assign.backend.domain.user.domain.service.UserService
import com.assign.backend.domain.user.infrastructure.repository.UserMapper
import com.assign.backend.global.exception.CustomBadRequestException
import com.assign.backend.global.jwt.JwtProvider
import com.assign.backend.global.jwt.TokenResult
import org.springframework.stereotype.Service

@Service
class AuthApplicationService(
    private val userService: UserService,
    private val jwtProvider: JwtProvider,
    private val loginLogJpaRepository: LoginLogJpaRepository,
) {

    fun signup(request: SignupCommand) {
        userService.validateEmail(request.email)
        val newUser = UserMapper.createUser(
            request.email,
            Password.encode(request.password),
            request.name
        )
        userService.save(newUser)
    }

    fun login(request: LoginCommand): TokenResult {
        val user = userService.getUserByEmail(request.email)
        if (!user.password.matches(request.password)) {
            throw CustomBadRequestException("이메일 또는 비밀번호가 잘못되었습니다.")
        }
        val token = jwtProvider.generateToken(user.id, user.role)
        // TODO: Login Log 예외로 두기
        loginLogJpaRepository.save(
            LoginLogEntity(user = UserMapper.toEntity(user))
        )
        return TokenResult(token)
    }

}