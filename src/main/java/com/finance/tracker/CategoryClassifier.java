package com.finance.tracker;

import java.util.HashMap;
import java.util.Map;

public class CategoryClassifier {

    private static final Map<String, String> KEYWORDS = new HashMap<>();

    static {
        KEYWORDS.put("salary", "Income");
        KEYWORDS.put("amazon", "Shopping");
        KEYWORDS.put("zomato", "Food");
        KEYWORDS.put("swiggy", "Food");
        KEYWORDS.put("uber", "Transport");
        KEYWORDS.put("petrol", "Transport");
        KEYWORDS.put("atm", "Cash Withdrawal");
    }

    public static String classify(String description) {
        if (description == null || description.trim().isEmpty()) {
            return "Other";
        }
        
        description = description.toLowerCase();
        for (String key : KEYWORDS.keySet()) {
            if (description.contains(key)) {
                return KEYWORDS.get(key);
            }
        }
        return "Other";
    }
}
