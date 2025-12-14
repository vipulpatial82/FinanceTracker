package com.finance.tracker;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FinanceController {

    private final TransactionManager transactionManager = new TransactionManager();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("summary", transactionManager.getSummary());
        model.addAttribute("transactions", transactionManager.getTransactions());
        return "index";
    }

    @PostMapping("/add-transaction")
    public String addTransaction(@RequestParam double amount,
                               @RequestParam String description,
                               @RequestParam boolean isIncome,
                               RedirectAttributes redirectAttributes) {
        try {
            String category = CategoryClassifier.classify(description);
            Transaction transaction = new Transaction(amount, category, description, isIncome);
            transactionManager.addTransaction(transaction);
            redirectAttributes.addFlashAttribute("message", 
                (isIncome ? "Income" : "Expense") + " added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportCSV() {
        try {
            String csvContent = transactionManager.generateCSVContent();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv"));
            headers.setContentDispositionFormData("attachment", "transactions.csv");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            
            return new ResponseEntity<>(csvContent.getBytes(), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}