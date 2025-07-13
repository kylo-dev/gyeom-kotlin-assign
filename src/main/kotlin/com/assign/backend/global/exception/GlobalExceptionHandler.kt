package com.assign.backend.global.exception

import com.assign.backend.global.util.logger
import com.assign.backend.global.response.ResponseData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException::class)
    fun authenticationExceptionHandler(e: AuthenticationException): ResponseEntity<Any> {
        logger.info { "AuthenticationException: $e" }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ResponseData.error<Any>("COMMON401", "로그인이 필요합니다."))
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun accessDeniedExceptionHandler(e: AccessDeniedException): ResponseEntity<Any> {
        logger.info { "AccessDeniedException: $e" }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ResponseData.error<Any>("COMMON403", "해당 요청에 대한 권한이 없습니다."))
    }

    @ExceptionHandler(CustomException::class)
    fun badRequestExceptionHandler(e: CustomException): ResponseEntity<Any> {
        logger.info { "CustomException $e" }
        return ResponseEntity.status(e.httpStatusCode)
            .body(ResponseData.error<Any>(e.code, e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<Any> {
        logger.info { "MethodArgumentNotValidException $e" }

        val fieldError: FieldError? = Objects.requireNonNull(e.bindingResult.fieldError)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ResponseData.error<Any>(
                    "COMMON400",
                    ValidationErrorDetail(
                        field = fieldError?.field.orEmpty(),
                        rejectedValue = fieldError?.rejectedValue,
                        message = fieldError?.defaultMessage.orEmpty()
                    )
                )
            )
    }

}