# 授权码模式Demo
此文档测试对象为`authorization-code-authorization-server`和`authorization-code-resource-server`
## 1. 获取授权码
1. 用户（资源拥有者）浏览器访问  
`http://localhost:8080/oauth/authorize?client_id=client-a&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read_user_info`
    请求参数列表：
    - client_id=客户端id
    - redirect_uri=回调url 一定要与授权服务器配置保持一致，否则得不到授权码
    - response_type=code 授权码必须是code
    - scope=作用域 与授权服务器配置保持一致
    - state=加密串或状态标识（可选）
    
2. 使用hellxz用户登录，用户名hellxz，密码xyz  
    > 这里使用授权服务器中的用户登录

3. 选择`Approve`，点`Authorize`提交， 完成授权操作 
4. 服务器校验通过，在回调url参数后添加`?code=授权码`并重定向
    > 回调的url对应一个服务接口，接收授权码并使用其换取令牌，下边授权码换令牌步骤使用手动方式测试

## 2. 授权码换令牌
> 换取令牌操作为服务器进行操作的，这里登录用户需要是客户端的标识与secret

1. 发送Post请求到`http://localhost:8080/oauth/token`  
    请求参数列表：
    - code=授权码
    - grant_type=authorization_code
    - redirect_uri=回调url
    - scope=作用域
    
    请求头列表：
    - Authorization:Basic 经Base64加密后的username:password的字符串

    ```
    curl -X POST \
      http://localhost:8080/oauth/token \
      -H 'Authorization: Basic Y2xpZW50LWE6Y2xpZW50LWEtc2VjcmV0' \
      -d 'code=TJb4Pd&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A9001%2Fcallback&scope=read_user_info'
    ```
    
    获得令牌
    ```json
    {
        "access_token": "3ed92b8a-86f0-4ee1-b309-7db0b513f554",
        "token_type": "bearer",
        "expires_in": 43199,
        "scope": "read_user_info"
    }
    ```

## 3. 校验Token

这里测试下授权服务的令牌校验端点`/oauth/check_token`
1. 发送Post请求`http://localhost:8080/oauth/check_token`
    请求参数列表：
    - token=令牌
    请求头参数：
    - Authorization=Basic Y2xpZW50LWE6Y2xpZW50LWEtc2VjcmV0
    
    ```
    curl -X POST http://localhost:8080/oauth/check_token \
        -H 'Authorization: Basic Y2xpZW50LWE6Y2xpZW50LWEtc2VjcmV0' \
        -d 'token=3ed92b8a-86f0-4ee1-b309-7db0b513f554'
    ```

    返回请求体：
    ```json
    {
        "aud": [
            "resource1"
        ],
        "active": true,
        "exp": 1574724686,
        "user_name": "hellxz",
        "client_id": "client-a",
        "scope": [
            "read_user_info"
        ]
    }
    ```
至此，我们确保了授权服务器的接口是正常的

## 4. 使用token访问受保护的资源
1. 发起请求`http://localhost:8081/user/hellxz001`
    请求头参数：
    - Authorization:Bearer 令牌值  这里要注意的是Bearer与token间有一个空格

    ```bash
    curl -X GET http://localhost:8081/user/hellxz001 \
         -H 'Authorization: Bearer ca3c1d03-67b8-4e39-b2b3-0d634f094610'
    ```

    返回结果
    ```json
    {
        "username": "hellxz001",
        "email": "hellxz001@foxmail.com"
    }
    ```

## 授权码流程梳理
1. 用户通过客户端(浏览器等)的接口 客户端本地检查是否有令牌，没有则拼接请求授权服务器的url重定向给用户，有则使用令牌尝试访问，如果请求过期，重新走授权
2. 用户访问到授权服务器页面，用户登录，并进行授权
3. 授权通过，授权服务器返回code（授权码）到回调的客户端url上
4. 客户端收到授权码，带上授权码向授权服务器发起请求, 获取token
4. 授权服务器根据传递过来的客户端的client_id、client_secret、授权码进行校验，校验通过返回access_token
5. 客户端收到token，将token保存在本地，请求时带上token，接着自调用接口，访问资源服务器的资源

> 这里暂未写客户端（浏览器这部分），通过手动获取资源以了解授权码模式整体授权流程