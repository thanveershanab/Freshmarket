# Installation and Running Instructions

## Prerequisites
-   Java 17 (Installed)
-   Maven (Installed at `C:\Users\ADMIN\.gemini\maven\apache-maven-3.9.6`)

## How to Run Directly

The easiest way to run the application is to use the provided PowerShell script in the project root:

1.  Open PowerShell in the project directory (`d:\java project`).
2.  Run the script:
    ```powershell
    .\run_app.ps1
    ```

## How to Run Manually

If you prefer to run the command yourself, use the following full path to the Maven executable:

```powershell
& "C:\Users\ADMIN\.gemini\maven\apache-maven-3.9.6\bin\mvn.cmd" spring-boot:run
```

## Accessing the App

Once the server starts (you see `Started GroceryApplication` in the logs):
-   Open your browser to: [http://localhost:8080](http://localhost:8080)
