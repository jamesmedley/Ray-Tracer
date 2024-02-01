@echo off

:main_loop
set /p arg1=Enter output filename (leave blank for default): 
set /p arg2=Enter scene file (leave blank for default: "2spheres.ser"): 

if "%arg1%"=="" set arg1="default.png"
if "%arg2%"=="" set arg2="2spheres.ser"

java -jar dist/Ray_Tracer.jar %arg1% %arg2%

if %errorlevel% equ 0 goto main_loop
pause


