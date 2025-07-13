package com.assign.backend.domain.analytics.controller

import com.assign.backend.domain.analytics.controller.dto.response.ActivitySummaryResponse
import com.assign.backend.domain.analytics.domain.service.AnalyticsService
import com.assign.backend.global.util.UrlConstant.ADMIN
import com.assign.backend.global.util.UrlConstant.ANALYTICS
import com.assign.backend.global.response.ResponseData
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ADMIN + ANALYTICS)
class AnalyticsController(
    private val analyticsService: AnalyticsService,
) {

    @GetMapping("/summary")
    fun getSummary(): ResponseData<ActivitySummaryResponse> {
        val todaySummary = analyticsService.getTodaySummary()
        return ResponseData.success(todaySummary.toResponse())
    }

    @GetMapping("/report")
    fun getCsvReport(): ResponseEntity<ByteArray> {
        val csvReport = analyticsService.generateTodayCsv()

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"daily_report.csv\"")
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(csvReport.toByteArray(Charsets.UTF_8))
    }
}