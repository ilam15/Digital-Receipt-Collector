package com.example.Digital_Receipt_Collector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Digital_Receipt_Collector.entity.Receipt;
import com.example.Digital_Receipt_Collector.service.ReceiptService;
import com.example.Digital_Receipt_Collector.service.UserService;
import com.example.Digital_Receipt_Collector.service.ReceiptCategoryService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/receipts")
public class ReceiptController {

	private final ReceiptService receiptService;
	private final UserService userService;
	private final ReceiptCategoryService categoryService;

	@Autowired
	public ReceiptController(ReceiptService receiptService, UserService userService, ReceiptCategoryService categoryService) {
		this.receiptService = receiptService;
		this.userService = userService;
		this.categoryService = categoryService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("receipts", receiptService.getAllReceipts());
		return "receipts/list";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("receipt", new Receipt());
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "receipts/form";
	}

	@PostMapping
	public String create(@ModelAttribute Receipt receipt,
						 @RequestParam("userId") Long userId,
						 @RequestParam("categoryId") Long categoryId,
						 @RequestParam(value = "date", required = false) String dateStr) {
		// attach user
		userService.getUserById(userId).ifPresent(receipt::setUser);
		// attach category
		categoryService.getById(categoryId).ifPresent(receipt::setCategory);
		// parse date-time from datetime-local input
		if (dateStr != null && !dateStr.isBlank()) {
			try {
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
				LocalDateTime dt = LocalDateTime.parse(dateStr, fmt);
				receipt.setDate(dt);
			} catch (Exception ex) {
				// ignore parse error, or optionally log
			}
		}
		receiptService.createReceipt(receipt);
		return "redirect:/receipts";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute("receipt", receiptService.getById(id).orElseThrow());
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "receipts/form";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, @ModelAttribute Receipt receipt,
						 @RequestParam("userId") Long userId,
						 @RequestParam("categoryId") Long categoryId,
						 @RequestParam(value = "date", required = false) String dateStr) {
		// attach user and category
		userService.getUserById(userId).ifPresent(receipt::setUser);
		categoryService.getById(categoryId).ifPresent(receipt::setCategory);
		if (dateStr != null && !dateStr.isBlank()) {
			try {
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
				LocalDateTime dt = LocalDateTime.parse(dateStr, fmt);
				receipt.setDate(dt);
			} catch (Exception ex) {
				// ignore parse error
			}
		}
		receiptService.updateReceipt(id, receipt);
		return "redirect:/receipts";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		receiptService.deleteReceipt(id);
		return "redirect:/receipts";
	}
}
