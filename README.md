# Project Name

This project focuses on automated testing of 
the Login and Checkout functionalities of the SauceDemo web application.
It is built using Java, Selenium WebDriver,
TestNG, with POM design pattern,
with test data managed through Excel files using Apache POI.

---

## Table of Contents

- [About The Project](#about-the-project)
- [Built With](#built-with)
- [Project Structure](#project-structure)
- [Test Coverage](#test-coverage)
- [Test Data](#test-data)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Git Workflow](#git-workflow)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

## About The Project

### Application Under Test

Link to application:

```
https://www.saucedemo.com/
```

### Goal


- Learning Selenium WebDriver
- Implementing Page Object Model
- Practicing Data Driven Testing
- Improving test automation skills

---

## Built With



### Languages

- Java

### Frameworks

- Selenium WebDriver
- TestNG

### Build Tool

- Maven

### Data Management

- Apache POI

### Version Control

- Git
- GitHub

### Additional Libraries

## Dependencies

The project is built using Maven and includes the following dependencies:

### UI Automation
- Selenium WebDriver (4.43.0) – browser automation framework
- Selenium Chrome Driver (4.43.0) – Chrome browser support

### Test Framework
- TestNG (7.12.0) – test execution and assertions

### Test Data Management
- Apache POI (5.2.5) – reading data from Excel files (Data Driven Testing)

### Driver Management
- WebDriverManager (6.3.4) – automatic driver setup for browsers

---

## Project Structure

```text
src
└── test
    └── java
        ├── Base
        ├── Pages
        ├── TestData
        └── Tests
```

### Base

- `BaseTest` class serves as the foundation for all test classes in the framework.
- `ExcelHelper` is a utility class that provides reusable methods for reading and managing test data from Excel files
- `ExcelReader` is a utility class that handles reading of Excel files using Apache POI and provides methods for extracting string and numeric data from specific sheets, rows, and cells
---

### Pages

Each page class is responsible for:

- Defining web element locators
- Encapsulating page-specific actions
- Providing reusable methods for test classes
- Improving maintainability and reducing code duplication

---

### TestData

The TestData package contains Excel files used for Data Driven Testing (DDT).

---

### Tests

The Tests package contains TestNG-based automated test classes that implement end-to-end, functional, and regression test scenarios for the SauceDemo application using Page Object Model and data-driven test data from Excel.

---

## Test Coverage

### Login Tests


- shouldLoginWithValidCredentials
- shouldShowErrorMessageWhenFieldsAreEmpty
- shouldShowErrorMessageWhenUsernameFieldIsEmpty
- shouldShowErrorMessageWhenPasswordFieldIsEmpty
- shouldShowErrorMessageWhenUserIsLockedOut
- shouldShowErrorMessageWhenCredentialsAreInvalid

---

### Item Page Tests


- shouldShowAllInventoryItemsAfterLogin 
- shouldDisplayOnlyUniqueItems
- shouldDisplayAllFilterOptionsInDropdown
- shouldShowCartBadgeAfterAddingItem



---

### End-to-End Tests



- shouldCompleteCheckoutHappyPath
- shouldShowErrorMessageWhenCheckoutFormIsEmpty
- shouldNavigateBackToInventoryOnCancelCheckout
- shouldCheckoutOnlyRemainingItemsAfterRemovingFromCart
- shouldReturnToLoginPageAfterLogout

---


## Installation

### Clone Repository

```bash
git clone <https://github.com/IlijaRastovic/JavaSeleniumPOM-DDT>
```

### Navigate to Project

```bash
cd <JavaSeleniumPOM-DDT>
```

### Install Dependencies

```bash
mvn clean install
```

---

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Suite

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run from IntelliJ IDEA

- Each test method can be executed individually by clicking the green "Run" icon next to the method name or by right-clicking the method and selecting "Run".
- A full test class can be executed by right-clicking the class file and selecting "Run 'ClassName'". This will execute all test methods inside that class.
---

## Git Workflow


```text
master
├── login-tests
├── itemPageTests
├── checkout-tests
```

### Typical Workflow

```bash
git checkout -b feature-branch

git add .

git commit -m "Implemented new test cases"

git push --set-upstream origin feature-branch
```

Merge feature branch into master after testing and review.

---

## Future Improvements

Planned project enhancements.

- Extent Reports
- Jenkins Integration
- Cross Browser Testing
- API Automation
- CI/CD Pipeline

---

## Author

Name: 

```
Ilija Rastovic
```

GitHub:

```
https://github.com/IlijaRastovic
```

LinkedIn:

```
www.linkedin.com/in/ilijar-qa-tester
```