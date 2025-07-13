package com.assign.backend.domain.chat.controller

import com.assign.backend.domain.chat.application.service.ChatApplicationService
import com.assign.backend.domain.chat.controller.dto.response.ChatResponse
import com.assign.backend.domain.chat.controller.dto.request.CreateChatRequest
import com.assign.backend.domain.chat.controller.dto.response.ThreadGroupResponse
import com.assign.backend.global.util.UrlConstant
import com.assign.backend.global.annotation.RequestInfo
import com.assign.backend.global.annotation.RequireAuth
import com.assign.backend.global.response.ResponseData
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlConstant.CHAT)
class ChatController(
    private val chatApplicationService: ChatApplicationService,
    private val requestInfo: RequestInfo,
) {

    @PostMapping
    @RequireAuth
    fun createChat(
        @RequestBody request: CreateChatRequest,
    ): ResponseData<ChatResponse> {
        val serviceRequest = request.toServiceRequest(requestInfo.user)
        return ResponseData.success(chatApplicationService.createChat(serviceRequest).toResponse())
    }

    @GetMapping
    @RequireAuth
    fun getChats(
        @PageableDefault(size = 10, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseData<List<ThreadGroupResponse>> {
        val threadGroupResults = chatApplicationService.getUserChats(requestInfo.user, pageable)
        return ResponseData.success(threadGroupResults.map { it.toResponse() })
    }

}