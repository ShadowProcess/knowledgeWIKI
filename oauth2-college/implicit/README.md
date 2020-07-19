## 简化模式（隐式授权模式）Demo
1. 客户端拼url重定向，用户登录并授权
    GET请求
    ```
    http://localhost:8080/oauth/authorize?client_id=client-a&redirect_uri=http://localhost:9001/callback&response_type=token&scope=read_user_info
    ```
    请求参数列表：
    - client_id=客户端id
    - redirect_uri=回调url 一定要与授权服务器配置保持一致，否则得不到授权码
    - response_type=token 简化模式必须是token
    - scope=作用域 与授权服务器配置保持一致
    - state=自定义串（可选）
    

    > `state`这个字段可选填，在生产环境下可以传加密串用来验证授权回调请求可信
    > 注意： 简化模式（隐式授权模式）不需要client_secret                                                                                                                                                                                          >
                                                                                                                                                                                                     >
    
2. 授权服务器返回token
    ```
    http://localhost:9001/callback#access_token=d87e1391-78a3-476f-afda-752e5884a93a&token_type=bearer&expires_in=119
    ```
3. 客户端保存token，并在请求时带上此token
   ```
   curl -X GET \
          http://localhost:8081/user/hellxz001 \
          -H 'Authorization: Bearer 0b68fd19-15a5-488e-bc80-dd918cd23cf8' \
   ```
   响应结果
   ```json
   {
       "username": "hellxz001",
       "email": "hellxz001@foxmail.com"
   }
   ```
   
## 简化模式流程梳理
1. 用户请求访问资源服务器资源，客户端（浏览器端）发现本地没有缓存用户授权token
2. 客户端拼url重定向到授权服务器请求页面
3. 用户登录，授权
4. 授权成功后，回调url返回token给客户端
5. 客户端收到token后，保存token并在请求时带上token，访问资源
