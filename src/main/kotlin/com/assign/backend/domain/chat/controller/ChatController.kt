package com.assign.backend.domain.chat.controller

import com.assign.backend.domain.chat.controller.dto.response.ChatResponse
import com.assign.backend.domain.chat.controller.dto.request.CreateChatRequest
import com.assign.backend.domain.chat.controller.dto.response.ThreadGroupResponse
import com.assign.backend.domain.chat.domain.service.ChatService
import com.assign.backend.global.UrlConstant
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
    private val chatService: ChatService,
    private val requestInfo: RequestInfo,
) {

    @PostMapping
    @RequireAuth
    fun createChat(
        @RequestBody request: CreateChatRequest,
    ): ResponseData<ChatResponse> {
        return ResponseData.success(chatService.createChat(requestInfo.user.id, request))
    }

    @GetMapping
    @RequireAuth
    fun getChats(
        @PageableDefault(size = 10, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseData<List<ThreadGroupResponse>> {
        return ResponseData.success(chatService.getUserChats(requestInfo.user.id, pageable))
    }

}