@echo off
set /p arg1=Enter filename (leave blank for default): 
java -jar dist/Ray_Tracer.jar %arg1%
pause
