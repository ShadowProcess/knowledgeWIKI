## 客户端模式DEMO
以下是我的理解：  
当用户发起一个访问资源服务的请求前，被客户端（浏览器）拦截到，它会先检查本地是否有token，  

如果token不存在就先发起一次Post请求，用自己的客户端账号密码获取token，  

然后使用此token添加到之前拦截到的请求头中，执行请求，返回资源

**关键点在于：这里没有用户登录与授权的部分，实际相对授权服务器登录的是客户端**

1. 发起请求，登录授权服务器获取token
    ```
    curl -X POST \
        --user user-center:12345 http://localhost:8080/oauth/token \
        -d "grant_type=client_credentials&scope=all"
    ```

    返回响应
    ```json
    {
        "access_token": "fc4721f4-c4a3-44f8-8232-a31cf59964ca",
        "token_type": "bearer",
        "expires_in": 43199,
        "scope": "all"
    }
    ```
2. 调用资源接口
   ```
   curl -X GET --user user-center:12345 http://localhost:8081/user/hellxz001
        -H 'Authorization: Bearer fc4721f4-c4a3-44f8-8232-a31cf59964ca'
   ```
   返回响应
   ```json
   {
       "username": "hellxz001",
       "email": "hellxz001@foxmail.com"
   }
   ```
   
## 校验token
另外，我这边尝试了一下校验token的接口，之前使用授权码等校验时，只需要传token、client_id、client_secret 
就可以正确校验token，

如果是client_credentials模式，校验接口时，需要使用client_id、client_secret登录，传参时只需要传token

```
curl -X POST --user user-center:12345  http://localhost:8080/oauth/check_token \
 -d 'token=b462bd1f-a50c-4209-ac06-5a668143ed9e'
```
返回结果
```json
{
    "scope": [
        "all"
    ],
    "active": true,
    "exp": 1574889562,
    "client_id": "user-center"
}
```