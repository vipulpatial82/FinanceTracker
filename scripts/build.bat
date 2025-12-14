@echo off
echo Building Finance Tracker Application...
mvn clean package -DskipTests
if %ERRORLEVEL% EQU 0 (
    echo Build successful!
    echo JAR file created: target\FinanceTracker-1.0-SNAPSHOT.jar
) else (
    echo Build failed!
    exit /b 1
)