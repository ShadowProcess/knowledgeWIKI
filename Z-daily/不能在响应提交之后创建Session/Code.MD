@GetMapping("WeChatService")
public ModelAndView theUserApproval() {
    ModelAndView mv = new ModelAndView("error_msg");
    if (CtlUtils.isNotWeChatBrowser()) {
        mv.addObject("errorMsg", "非微信浏览器");
        return mv;
    }
    String weChatAuthUrl = weChatService.getWeChatAuthUrl();
    try {
        //响应302
        CtlUtils.response().sendRedirect(weChatAuthUrl);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return mv;  //这个地方返回mv，将会报错，应该返回null
}

//原因解释：
第一次请求：
服务器和客户端建立连接，第11行代码直接提交响应给客户端，并关闭此次响应。
在第15行代码时，你又返回给客户端mv，那么你需要重新创建Session，由于此次响应已经提交，服务器无法获取到和该客户端的SessionId了
所以，报错，不能再响应提交之后创建Session

第二次请求：
客户端带着SessionID来请求服务器，所以第11行代码响应提交之后，第15行重新创建会话是可以的，因为有SessionID，所以可以继续响应给客户端




###linux文件重命名命令

rename 原字符串 目标字符串 文件

原字符串：将文件名需要替换的字符串；
目标字符串：将文件名中含有的原字符替换成目标字符串；
文件：指定要改变文件名的文件列表。

二、示列
# 将main1.c重命名为main.c
rename main1.c main.c main1.c