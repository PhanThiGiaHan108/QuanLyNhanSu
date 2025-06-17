package com.raven.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtils {
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
    
    static {
        if (CURRENCY_FORMAT instanceof DecimalFormat) {
            ((DecimalFormat) CURRENCY_FORMAT).applyPattern("#,###");
        }
    }
    
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return DATE_FORMAT.format(date);
    }
    
    public static String formatCurrency(double amount) {
        return CURRENCY_FORMAT.format(amount);
    }
    
    public static Date parseDate(String dateStr) {
        try {
            if (dateStr == null || dateStr.isEmpty()) {
                return null;
            }
            return DATE_FORMAT.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static double parseCurrency(String amount) {
        try {
            if (amount == null || amount.isEmpty()) {
                return 0;
            }
            
            // Check if the amount starts with a negative sign
            boolean isNegative = amount.trim().startsWith("-");
            
            // Remove all non-numeric characters except decimal point
            String cleanAmount = amount.replaceAll("[^0-9.]", "");
            double value = Double.parseDouble(cleanAmount);
            
            // Apply negative sign if needed
            return isNegative ? -value : value;
        } catch (Exception e) {
            return 0;
        }
    }
} 