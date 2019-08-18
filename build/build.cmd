@echo off

set PJ_D_ROOT=%~dp0..

rem External Tools: java (from JDK)
set JAVA=java

rem Detect java
%JAVA% -version 1>nul 2>&1
if errorlevel 1 (
   echo %JAVA%: NOT FOUND
   exit /b 1
)
echo %JAVA%: Found in path

cd "%PJ_D_ROOT%"

rem .\gradlew.bat clean assemble test
.\gradlew.bat clean assemble test