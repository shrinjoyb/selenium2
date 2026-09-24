# OrangeHRM Selenium Test Automation Framework

This project is an industry-standard Page Object Model (POM) Selenium automation framework built to test negative login scenarios on the OrangeHRM demo portal (`https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`).

## Key Features & Structure
- **Framework Type:** Page Object Model (POM) + Data-Driven Testing (DDT)
- **Programming Language:** Java 17
- **Test Runner:** TestNG 7.10.2
- **Data-Driven Engine:** Apache POI reading test scenarios from Excel (`src/test/resources/testdata/OrangeHRM_TestData.xlsx`)
- **Reporting:** ExtentReports 5.x with automatic Base64 screenshot captures on Test Start, Pass, and Failure
- **Config Management:** Centralized `config.properties` loaded via `ConfigurationManager`
- **Logging:** Apache Log4j2

---

## Project Structure
```
D:\Shrinjoy_Resume\Practice Project\
├── pom.xml
├── testng.xml
├── runner/
│   └── login_suite.xml
├── report/
│   ├── OrangeHRM_Negative_Login_Report.html
│   └── screenshots/
└── src/
    └── test/
        ├── java/
        │   ├── configuration/
        │   │   └── ConfigurationManager.java
        │   ├── utility/
        │   │   ├── BrowserUtil.java
        │   │   ├── WaitUtil.java
        │   │   ├── ElementUtil.java
        │   │   ├── ExcleReader.java
        │   │   └── ExcleDataProvider.java
        │   ├── report/
        │   │   └── extentreport/
        │   │       ├── ExtentReportManager.java
        │   │       └── ExtentTestManager.java
        │   ├── listeners/
        │   │   └── TestListener.java
        │   ├── pages/
        │   │   └── LoginPage.java
        │   ├── tests/
        │   │   ├── base/
        │   │   │   └── BaseTest.java
        │   │   └── login/
        │   │       ├── LoginAssertion.java
        │   │       └── LoginNegativeTest.java
        │   └── DataProvider/
        │       └── LoginDataProvider.java
        └── resources/
            ├── config.properties
            ├── log4j2.xml
            └── testdata/
                └── OrangeHRM_TestData.xlsx
```

---

## Test Scenarios Covered (Excel Driven)
| TestCaseID | Scenario Description | Username | Password | Expected Error Type | Expected Message |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **TC01** | Both username and password invalid | `InvalidUser` | `InvalidPass123` | `ALERT` | `Invalid credentials` |
| **TC02** | Valid username and invalid password | `Admin` | `WrongPassword999` | `ALERT` | `Invalid credentials` |
| **TC03** | Invalid username and valid password | `WrongUserAdmin` | `admin123` | `ALERT` | `Invalid credentials` |
| **TC04** | Blank username and valid password | `[EMPTY]` | `admin123` | `USERNAME_REQUIRED` | `Required` |
| **TC05** | Valid username and blank password | `Admin` | `[EMPTY]` | `PASSWORD_REQUIRED` | `Required` |
| **TC06** | Both username and password blank | `[EMPTY]` | `[EMPTY]` | `BOTH_REQUIRED` | `Required` |

---

## How to Execute the Tests

### 1. From Terminal / Command Prompt:
```bash
cd "D:\Shrinjoy_Resume\Practice Project"
mvn clean test
```

### 2. Run with Custom Browser / Headless Mode:
You can adjust `src/test/resources/config.properties`:
```properties
browser=chrome
headless=false   # set to true for CI/CD or headless execution
```

### 3. View Extent Report:
Open `report/OrangeHRM_Negative_Login_Report.html` in any browser to see the interactive execution results with embedded screenshots.
