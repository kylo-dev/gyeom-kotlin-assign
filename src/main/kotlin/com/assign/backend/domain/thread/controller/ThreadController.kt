package com.assign.backend.domain.thread.controller

import com.assign.backend.domain.thread.service.ThreadService
import com.assign.backend.global.UrlConstant
import com.assign.backend.global.annotation.RequestInfo
import com.assign.backend.global.response.ResponseData
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlConstant.THREAD)
class ThreadController(
    private val threadService: ThreadService,
    private val requestInfo: RequestInfo,
) {

    @DeleteMapping("/{threadId}")
    fun deleteThread(
        @PathVariable threadId: Long,
    ): ResponseData<String> {
        threadService.deleteThread(requestInfo.user.id, threadId)
        return ResponseData.success("스레드 삭제에 성공했습니다.")
    }
}