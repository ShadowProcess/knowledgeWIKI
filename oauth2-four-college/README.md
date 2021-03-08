# spring-security-oauth2-learn
## 仓库说明
此项目用于学习Spring Security OAuth2，Demo使用的Api为5.2版本前的，现在提示已经废弃，ps：官方认为自己的授权服务器实现的太差，以后打算用第三方库 :happy:

后续有时间使用官方推荐第三方库还会再实现一遍

另外，本仓库均使用授权服务与资源服务分离的方式来编写demo，由于此二者放在同一服务时默认省了很多配置（资源服务器访问授权服务器进行校验token部分），对于后续应用会埋下许多坑，所以分离开来

## 基础演示demo功能说明
> 基础Demo均使用内存保存token，仅用于测试

- [x] authorization-code : 授权码模式

- [x] client-credentials : 客户端模式

- [x] implicit: 隐式模式（简化模式）

- [x] password: 密码模式

## client端对接demo
- [x] authorization-code/authorization-code-client-resttemplate-jdbc : 使用RestTemplate和数据库实现的授权码模式手动对接客户端，token保存到数据库
- [x] uaa-interface-adapter-demo ： 使用OAuth2提供的工具类，实现的客户端模式与密码模式的登录功能适配，也可以作为不显示使用/oauth/token端点的适配层，提升代码灵活性
- [x] client-credentials/client-credentials-client: 实现一个客户端，如果调用其它服务的请求头中没有登录标识，使用客户端模式获取token，调用其它服务

## JWT
> 使用rsa非对称加密，对称加密请参考本目录之前的提交

- [x] jwt-authorization-server : 添加JWT实现token的授权服务器，这里开启了授权码与密码模式，支持refresh_token
- [x] jwt-resource-server：资源服务器，本地校验jwt token，解析出用户信息

## 使用Redis存储token
- [x] redis-token-saved-authorization-server : 使用password与授权码模式，支持刷新token的授权服务器，token保存到redis中
- [x] redis-token-saved-resource-server : 使用redis校验token的资源服务器


