@echo off
echo subindo conteiner oracle ....
echo .
echo.
echo Executando um docker ps para mostrar o conteiner UP
echo Se aparecer um hash abaixo, eh pq subiu o conteiner
docker run -d -p 49160:22 -p 1521:1521 -e ORACLE_ALLOW_REMOTE=true wnameless/oracle-xe-11g
pause