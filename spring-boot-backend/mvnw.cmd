@echo off
setlocal

set SCRIPT_DIR=%~dp0
set TOOL_DIR=%SCRIPT_DIR%.mvn\wrapper
set MVNW_VERSION=0.6.0

if not exist "%TOOL_DIR%" mkdir "%TOOL_DIR%"
if not exist "%TOOL_DIR%\maven-wrapper.jar" curl -o "%TOOL_DIR%\maven-wrapper.jar" https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.6.0/maven-wrapper-0.6.0.jar
if not exist "%TOOL_DIR%\maven-wrapper.properties" curl -o "%TOOL_DIR%\maven-wrapper.properties" https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.6.0/maven-wrapper-0.6.0.properties
if not exist "%TOOL_DIR%\MavenWrapperDownloader.java" curl -o "%TOOL_DIR%\MavenWrapperDownloader.java" https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.6.0/maven-wrapper-0.6.0.jar

if not exist "%TOOL_DIR%\maven-wrapper.jar" goto error
if not exist "%TOOL_DIR%\maven-wrapper.properties" goto error
if not exist "%TOOL_DIR%\MavenWrapperDownloader.java" goto error

set JAVA_EXE=java
if defined JAVA_HOME set JAVA_EXE=%JAVA_HOME%\bin\java.exe

%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% neq 0 goto error

%JAVA_EXE% -classpath "%TOOL_DIR%\maven-wrapper.jar" "-Dmaven.home=%TOOL_DIR%" "-Dmaven.multiModuleProjectDirectory=%CD%" io.takari.maven.wrapper.MavenWrapperDownloader %MVNW_VERSION%

if %ERRORLEVEL% neq 0 goto error

%JAVA_EXE% -classpath "%TOOL_DIR%\maven-wrapper.jar" "-Dmaven.home=%TOOL_DIR%" "-Dmaven.multiModuleProjectDirectory=%CD%" org.apache.maven.wrapper.MavenWrapperMain %*
goto end

:error
echo Error occurred during initialization of Maven Wrapper
echo Check if you have a working internet connection and try again.
echo If you continue to experience issues, please contact your project administrator.

:end
endlocal