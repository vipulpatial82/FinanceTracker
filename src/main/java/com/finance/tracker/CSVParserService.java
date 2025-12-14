package com.finance.tracker;

import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVParserService {

    public void parseCSV(String filePath, TransactionManager tm) {
        if (filePath == null || tm == null) {
            throw new IllegalArgumentException("File path and transaction manager cannot be null");
        }
        
        try {
            Path path = Paths.get(filePath).normalize();
            if (!path.isAbsolute() || path.toString().contains("..")) {
                throw new IllegalArgumentException("Invalid file path");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file path: " + e.getMessage());
        }

        try (CSVParser parser = new CSVParser(new FileReader(filePath),
                CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord record : parser) {
                String desc = record.get("Description");
                String amountStr = record.get("Amount");
                
                if (desc == null || amountStr == null) {
                    continue; 
                }
                
                double amount = Double.parseDouble(amountStr);
                boolean isIncome = amount > 0;
                String category = CategoryClassifier.classify(desc);

                tm.addTransaction(new Transaction(
                        Math.abs(amount),
                        category,
                        desc,
                        isIncome
                ));
            }

            System.out.println("CSV processed successfully.");

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in CSV: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing CSV: " + e.getMessage());
        }
    }
}
