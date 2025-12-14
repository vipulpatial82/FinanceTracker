package com.finance.tracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManager {

    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public Map<String, Object> getSummary() {
        double income = 0, expense = 0;
        int incomeCount = 0, expenseCount = 0;

        for (Transaction t : transactions) {
            if (t.isIncome()) {
                income += t.getAmount();
                incomeCount++;
            } else {
                expense += t.getAmount();
                expenseCount++;
            }
        }

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalTransactions", transactions.size());
        summary.put("incomeCount", incomeCount);
        summary.put("expenseCount", expenseCount);
        summary.put("totalIncome", income);
        summary.put("totalExpense", expense);
        summary.put("balance", income - expense);
        return summary;
    }

    public void printSummary() {
        double income = 0, expense = 0;
        int incomeCount = 0, expenseCount = 0;

        for (Transaction t : transactions) {
            if (t.isIncome()) {
                income += t.getAmount();
                incomeCount++;
            } else {
                expense += t.getAmount();
                expenseCount++;
            }
        }

        System.out.println("\n====== FINANCE SUMMARY ======");
        System.out.println("Total Transactions: " + transactions.size());
        System.out.println("Income Transactions: " + incomeCount);
        System.out.println("Expense Transactions: " + expenseCount);
        System.out.println("------------------------------");
        System.out.println("Total Income  : $" + String.format("%.2f", income));
        System.out.println("Total Expense : $" + String.format("%.2f", expense));
        System.out.println("Balance       : $" + String.format("%.2f", (income - expense)));
        System.out.println("==============================");
    }

    public String generateCSVContent() {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Description,Amount,Category,Type\n");
        
        for (Transaction t : transactions) {
            double amount = t.isIncome() ? t.getAmount() : -t.getAmount();
            csvContent.append(String.format("%s,%.2f,%s,%s\n", 
                escapeCSV(t.getDescription()), amount, escapeCSV(t.getCategory()), 
                t.isIncome() ? "Income" : "Expense"));
        }
        
        return csvContent.toString();
    }
    
    private String escapeCSV(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    public void exportToCSV() throws IOException {
        String downloadsPath = System.getProperty("user.home") + "\\Downloads\\my_transactions.csv";
        try (FileWriter writer = new FileWriter(downloadsPath)) {
            writer.write(generateCSVContent());
        }
        System.out.println("Transactions exported to: " + downloadsPath);
    }
}
