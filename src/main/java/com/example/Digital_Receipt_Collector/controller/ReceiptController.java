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

@Controller
@RequestMapping("/receipts")
public class ReceiptController {

	private final ReceiptService receiptService;

	@Autowired
	public ReceiptController(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("receipts", receiptService.getAllReceipts());
		return "receipts/list";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("receipt", new Receipt());
		return "receipts/form";
	}

	@PostMapping
	public String create(@ModelAttribute Receipt receipt) {
		receiptService.createReceipt(receipt);
		return "redirect:/receipts";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute("receipt", receiptService.getById(id).orElseThrow());
		return "receipts/form";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, @ModelAttribute Receipt receipt) {
		receiptService.updateReceipt(id, receipt);
		return "redirect:/receipts";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		receiptService.deleteReceipt(id);
		return "redirect:/receipts";
	}
}
