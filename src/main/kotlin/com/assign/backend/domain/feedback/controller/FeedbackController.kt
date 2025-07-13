//package com.assign.backend.domain.feedback.controller
//
//import com.assign.backend.domain.feedback.controller.dto.request.CreateFeedbackRequest
//import com.assign.backend.domain.feedback.controller.dto.request.UpdateFeedbackStatusRequest
//import com.assign.backend.domain.feedback.controller.dto.response.FeedbackResponse
//import com.assign.backend.domain.feedback.service.FeedbackService
//import com.assign.backend.global.UrlConstant
//import com.assign.backend.global.annotation.RequestInfo
//import com.assign.backend.global.response.ResponseData
//import org.springframework.data.domain.Pageable
//import org.springframework.data.domain.Sort
//import org.springframework.data.web.PageableDefault
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping(UrlConstant.FEEDBACK)
//class FeedbackController(
//    private val feedbackService: FeedbackService,
//    private val requestInfo: RequestInfo,
//) {
//
//    @PostMapping
//    fun createFeedback(
//        @RequestBody request: CreateFeedbackRequest
//    ): ResponseData<String> {
//        feedbackService.createFeedback(requestInfo.user.id, request)
//        return ResponseData.success("피드백 생성에 성공했습니다.")
//    }
//
//    @PatchMapping("/{feedbackId}/status")
//    fun updateStatus(
//        @PathVariable feedbackId: Long,
//        @RequestBody request: UpdateFeedbackStatusRequest
//    ): ResponseData<String> {
//        feedbackService.updateStatus(feedbackId, request)
//        return ResponseData.success("피드백 상태 변경에 성공했습니다.")
//    }
//
//    @GetMapping
//    fun getFeedbacks(
//        @RequestParam positive: Boolean?,
//        @PageableDefault(
//            size = 10,
//            sort = ["createdAt"],
//            direction = Sort.Direction.DESC
//        ) pageable: Pageable
//    ): ResponseData<List<FeedbackResponse>> {
//        val results = feedbackService.getFeedbackList(requestInfo.user.id, positive, pageable)
//        return ResponseData.success(results)
//    }
//}