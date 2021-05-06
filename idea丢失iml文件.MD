Load Settings
Cannot load settings from file 'D:\DevelopmentSoftWare\workstation\ill-circle\ill-circle.iml': File D:\DevelopmentSoftWare\workstation\ill-circle\ill-circle.iml does not exist
Please correct the file content


# idea项目缺少iml文件,在该项目下执行命令:
# mvn idea:module



# mvn -U 和 -N 参数：

>关于-N
```TEXT
-N,--non-recursive (Do not recurse into sub-projects)

意思是，不递归到子项目(子模块)。

举例：
一个父项目下Father面有3个子项目A、B、C，都生成jar包，则有Father.jar、A.jar、B.jar、C.jar;
这个时候A项目依赖了B、C项目。

此时如果使用mvn clean install -N，则只会把Father.jar安装到本地仓库(~/.m2/repository)，
而不会安装其他三个包

造成的问题是，如果你此时使用如下命令拷贝依赖包，则会报错：说找不到依赖包B.jar/C.jar

mvn dependency:copy-dependencies -DoutputDirectory=$WORKSPACE/dependencies
```


>关于-U
```TEXT
-U,--update-snapshots (Forces a check for missing releases)
and updated snapshots on remote repositories

意思是：强制刷新本地仓库不存在release版和所有的snapshots版本。

对于release版本，本地已经存在，则不会重复下载
对于snapshots版本，不管本地是否存在，都会强制刷新，但是刷新并不意味着把jar重新下载一遍。
只下载几个比较小的文件，通过这几个小文件确定本地和远程仓库的版本是否一致，再决定是否下载
```
