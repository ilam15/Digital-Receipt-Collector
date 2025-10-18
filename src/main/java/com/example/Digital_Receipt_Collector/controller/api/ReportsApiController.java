package com.example.Digital_Receipt_Collector.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Digital_Receipt_Collector.service.ReportsService;

import java.time.YearMonth;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportsApiController {

    private final ReportsService reportsService;

    @Autowired
    public ReportsApiController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("/user/{id}/monthly")
    public Map<String, Object> monthly(@PathVariable Long id, @RequestParam(required = false) String month) {
        YearMonth m = (month == null) ? YearMonth.now() : YearMonth.parse(month);
        return reportsService.monthlyReportForUser(id, m);
    }

    @GetMapping("/user/{id}/category")
    public Map<String, Double> categoryWise(@PathVariable Long id) {
        return reportsService.categoryWiseForUser(id);
    }
}
