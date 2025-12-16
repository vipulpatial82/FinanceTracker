# Finance Tracker Web Application

A modern web-based personal finance tracker built with Spring Boot and Thymeleaf.

## Features

- 💰 Add income and expense transactions
- 🏷️ Automatic category classification
- 📊 Real-time financial summary dashboard
- 📁 Export transactions to CSV
- 🎨 Modern, responsive UI
- 📱 Mobile-friendly design

## How to Run
### Using Maven
1. Run: `mvn spring-boot:run`
2. Open your browser and go to the URL shown in the console

## Usage

1. **Add Transactions**: Enter amount and description, then click "+ Income" or "- Expense"
2. **View Summary**: See your total income, expenses, and balance at the top
3. **Export Data**: Click "📊 Export to CSV" to download your transactions
4. **Categories**: Transactions are automatically categorized based on keywords

## Category Classification

The app automatically classifies transactions based on keywords:
- "salary" → Income
- "amazon" → Shopping  
- "zomato", "swiggy" → Food
- "petrol" → Transport
- "atm" → Cash Withdrawal
- Others → Other

## Technical Details

- **Backend**: Spring Boot 2.7.0
- **Frontend**: Thymeleaf + HTML/CSS
- **Build Tool**: Maven
- **Java Version**: 11+

## Troubleshooting

If you get a "Port already in use" error, the application will automatically find an available port and display it in the console.

## Project Structure

```
FinanceTracker/
├── src/main/java/com/finance/tracker/
│   ├── App.java                 # Main application
│   ├── FinanceController.java   # Web controller
│   ├── Transaction.java         # Transaction model
│   ├── TransactionManager.java  # Business logic
│   ├── CategoryClassifier.java  # Auto-categorization
│   └── CSVParserService.java    # CSV import/export
├── src/main/resources/
│   ├── templates/index.html     # Main web page
│   └── application.properties   # Configuration
├── docs/
│   ├── DevOps_Synopsis.txt      # DevOps documentation
│   └── API_Documentation.md     # API reference
├── scripts/
│   ├── start.bat               # Application startup
│   ├── build.bat               # Build script
│   └── docker-build.bat        # Docker build script
├── config/
│   └── docker-compose.yml      # Docker Compose config
├── Dockerfile                   # Container configuration
├── pom.xml                     # Maven configuration
└── README.md                   # This file

