# Spring Transaction Analysis Application

This is a sample Spring Boot application for analyzing transactions. The application uses the `Transaction` class to represent transactions and performs various operations on them asynchronously.
The result will display in Website http://localhost:8080/financial/Analysis

## Project Structure
'''
spring-transaction-analysis/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── analysis/
│   │   │       ├── FinancialPlatform.java
│   │   │       ├── FinancialPlatformController.java
│   │   │       ├── Transaction.java
│   │   │       └── TransactionService.java
│   │   └── resources/
│   │       └── template/
│   │       │   ├── application.properties
│   │       │   ├── results.html
│   │       └── transactions.csv
│   └── test/
│       └── java
└── pom.xml
'''


## How to use
1. Change absolute path in file TransactionService.java
2. Build the project using Maven:

