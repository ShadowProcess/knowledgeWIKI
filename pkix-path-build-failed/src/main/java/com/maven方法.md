## idea增加maven的配置   
## 或者每次运行mvn命令加上
-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true

## 这个参数的作用是让idea在控制台随意使用mvn命令
-Dmaven.multiModuleProjectDirectory=$MAVEN_HOME

