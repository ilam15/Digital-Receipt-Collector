package com.example.Digital_Receipt_Collector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/app"})
    public String home(Model model) {
        // any attributes the template expects can be added here; template uses only current year via Thymeleaf
        return "App/App";
    }

    @GetMapping("/reports")
    public String reports() {
        // placeholder: for now redirect to App landing (or implement reports page later)
        return "App/App";
    }
}
