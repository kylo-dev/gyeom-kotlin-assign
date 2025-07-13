package com.assign.backend.domain.feedback.controller

import com.assign.backend.domain.feedback.application.dto.reqeust.FeedbackQuery
import com.assign.backend.domain.feedback.application.service.FeedbackApplicationService
import com.assign.backend.domain.feedback.controller.dto.request.CreateFeedbackRequest
import com.assign.backend.domain.feedback.controller.dto.request.UpdateFeedbackStatusRequest
import com.assign.backend.domain.feedback.controller.dto.response.FeedbackResponse
import com.assign.backend.domain.feedback.domain.service.FeedbackService
import com.assign.backend.global.UrlConstant
import com.assign.backend.global.annotation.RequestInfo
import com.assign.backend.global.response.ResponseData
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlConstant.FEEDBACK)
class FeedbackController(
    private val feedbackService: FeedbackService,
    private val feedbackApplicationService: FeedbackApplicationService,
    private val requestInfo: RequestInfo,
) {

    @PostMapping
    fun createFeedback(
        @RequestBody request: CreateFeedbackRequest
    ): ResponseData<String> {
        feedbackApplicationService.createFeedback(request.toServiceRequest(requestInfo.user))
        return ResponseData.success("피드백 생성에 성공했습니다.")
    }

    @PatchMapping("/{feedbackId}/status")
    fun updateStatus(
        @PathVariable feedbackId: Long,
        @RequestBody request: UpdateFeedbackStatusRequest
    ): ResponseData<String> {
        feedbackService.updateStatus(request.toServiceRequest(feedbackId))
        return ResponseData.success("피드백 상태 변경에 성공했습니다.")
    }

    @GetMapping
    fun getFeedbacks(
        @RequestParam positive: Boolean?,
        @PageableDefault(
            size = 10,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseData<List<FeedbackResponse>> {
        val serviceRequest = FeedbackQuery.of(requestInfo.user, positive, pageable)
        val results = feedbackService.getFeedbackList(serviceRequest)
        return ResponseData.success(results.map { FeedbackResponse.from(it) })
    }
}