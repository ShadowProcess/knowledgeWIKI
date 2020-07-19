package main.javaCode.com.control;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Commander {

    /**
     * 将ServiceImpl注入Service,根据注解注入
     **/
    @Autowired
    UserService userService;

//    @RequestMapping(value = "/user/login")
//    public String loginVerify(HttpServletRequest request, Model model) {
//        String inputUserName = request.getParameter("userName");
//        String inputPassWord = request.getParameter("passWord");
//        User user = userService.getUserByUserName(inputUserName);
//
//        if (user != null) {
//            System.out.println(user.toString());
//            if (user.getPassword().equals(inputPassWord)) {
//                System.out.println("登录成功呢");
//                model.addAttribute("flag", "登录成功呢");
//            } else {
//                System.out.println("用户密码错误!");
//                model.addAttribute("flag", "用户密码错误");
//            }
//        } else {
//            System.out.println("用户不存在!");
//            model.addAttribute("flag", "用户不存在");
//        }
//        return "success";
//    }

    /**
     * 异步登录
     * 1.前台使用ajax请求提交数据
     * 2.注意:后台返回的数据一定要和Ajax返回数据类型对应,返回JSON数据需要在springMVC.xml中进行配置
     */
    @RequestMapping(value = "/user/ajaxLogin",method = RequestMethod.POST)
    @ResponseBody
    public String ajaxLogin(HttpServletRequest request) {
        String inputUserName =request.getParameter("userName");
        String inputPassWord = request.getParameter("passWord");
        User user = userService.getUserByUserName(inputUserName);

        if (user != null) {
            System.out.println(user.toString());
            if (user.getPassword().equals(inputPassWord)) {
                System.out.println("登录成功呢");
                return "success";
            } else {
                System.out.println("用户密码错误!");
                return "passWordFail";
            }
        } else {
            System.out.println("用户不存在!");
            return "nonentity";
        }
    }

    /**登录成功呢**/
    @RequestMapping(value = "/user/loginSuccess")
    public String loginSuccess(){
        return "success";
    }

}
