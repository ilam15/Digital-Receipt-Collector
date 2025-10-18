package com.example.Digital_Receipt_Collector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Digital_Receipt_Collector.entity.ReceiptCategory;
import com.example.Digital_Receipt_Collector.service.ReceiptCategoryService;

@Controller
@RequestMapping("/categories")
public class ReceiptCatogoriesController {

	private final ReceiptCategoryService service;

	@Autowired
	public ReceiptCatogoriesController(ReceiptCategoryService service) {
		this.service = service;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("categories", service.getAllCategories());
		return "categories/list";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("category", new ReceiptCategory());
		return "categories/form";
	}

	@PostMapping
	public String create(@ModelAttribute ReceiptCategory category) {
		service.createCategory(category);
		return "redirect:/categories";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute("category", service.getById(id).orElseThrow());
		return "categories/form";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, @ModelAttribute ReceiptCategory category) {
		service.updateCategory(id, category);
		return "redirect:/categories";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		service.deleteCategory(id);
		return "redirect:/categories";
	}
}
