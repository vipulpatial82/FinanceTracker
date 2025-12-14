package com.finance.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        
        if (context instanceof ServletWebServerApplicationContext) {
            ServletWebServerApplicationContext webContext = (ServletWebServerApplicationContext) context;
            int port = webContext.getWebServer().getPort();
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🚀 Finance Tracker is running!");
            System.out.println("📱 Open your browser and go to: http://localhost:" + port);
            System.out.println("🛑 Press Ctrl+C to stop the application");
            System.out.println("=".repeat(60) + "\n");
        }
    }
}
