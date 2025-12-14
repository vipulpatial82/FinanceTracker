@echo off
echo Building Docker image for Finance Tracker...
call build.bat
if %ERRORLEVEL% EQU 0 (
    docker build -t finance-tracker:latest .
    echo Docker image built successfully!
) else (
    echo Failed to build application. Docker build aborted.
)