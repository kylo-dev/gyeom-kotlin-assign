package com.assign.backend.global.interceptor

import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.global.annotation.RequestInfo
import com.assign.backend.global.annotation.RequireAuth
import com.assign.backend.global.exception.CustomAuthenticationException
import com.assign.backend.global.security.CustomAuthentication
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.kotlinFunction

@Component
class RequestInterceptor(
    private val requestInfo: RequestInfo
) : HandlerInterceptor {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val user = getAuthentication()

        (handler as? HandlerMethod)?.let { handlerMethod ->
            val kFunction = handlerMethod.method.kotlinFunction
            if (kFunction?.hasAnnotation<RequireAuth>() == true && user == null) {
                throw CustomAuthenticationException("로그인이 필요합니다")
            }
        }
        user?.let { requestInfo.setRequestMember(it) }
        return true
    }

    fun getAuthentication(): User? =
        (SecurityContextHolder.getContext().authentication as? CustomAuthentication)?.user
}
