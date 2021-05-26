REM 声明采用UTF-8编码(注释)
chcp 65001
echo "设置控制台UTF-8编码"


SET JAVA_HOME=C:\Program Files\Java\jdk-11.0.8
SET Classpath=%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\lib\dt.jar;
SET Path=%JAVA_HOME%\bin;
echo "Java临时环境变量设置成功"


cd \
D:
cd D:\DevelopmentSoftWare\Burp Suite Pro v2020.12.1

java.exe -noverify -javaagent:BurpSuiteLoader.jar -jar burpsuite_pro_v2020.12.1.jar
