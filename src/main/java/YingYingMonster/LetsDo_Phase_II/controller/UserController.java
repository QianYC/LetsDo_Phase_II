package YingYingMonster.LetsDo_Phase_II.controller;

import YingYingMonster.LetsDo_Phase_II.exception.LoginFailException;
import YingYingMonster.LetsDo_Phase_II.model.Publisher;
import YingYingMonster.LetsDo_Phase_II.model.User;
import YingYingMonster.LetsDo_Phase_II.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/publisherSignUp")
//    @ApiOperation(value = "访问用户注册界面")
    public String visitRegisterPage(){
        return "user/signUp";
    }

    @PostMapping("/publisherSignUp")
//    @ApiOperation(value = "注册新用户，注册成功后跳转至登录界面；失败则返回注册界面，显示错误信息")
    public String register(@RequestParam("userId")String userId
            ,@RequestParam("password")String password
            ,@RequestParam("nickName")String nickName){
        Publisher publisher=new Publisher();
        publisher.setId(userId);
        publisher.setName(nickName);
        publisher.setPw(password);
        if(userService.register(publisher))
            return "redirect:/user/login";
        else
            return "user/publisherSignUp";
    }

    @GetMapping("/login")
//    @ApiOperation(value = "访问用户登录界面")
    public String visitLoginPage(){
        return "user/login";
    }

    @PostMapping("/login")
//    @ApiOperation(value = "用户登录，成功后返回用户工作界面；失败则返回登录界面，显示错误信息")
    /**
     * 表单里的input模块的name属性决定了参数名
     */
    public String login(@RequestParam("userId")String userId
            ,@RequestParam("password")String password){
        try {
            User user = userService.login(userId, password);
        }catch (LoginFailException e){
            return "user/login";
        }
        return "redirect:/publisherPage/publish";
    }

    @GetMapping("/modify")
//    @ApiOperation(value="访问修改信息页面",notes="一定要根据账号密码做身份验证，否则可能会修改到别的用户的数据！")
    public String visitModifyPage(Model model,
                                  @RequestParam(value="id",required=true)String id,
                                  @RequestParam(value="pw",required=true)String pw){

        User user=userService.getUser(id);
        model.addAttribute("user", user);
//        if(user.getPw().equals(pw))
//            return "workSpace";
//        else
            return "error";//身份验证失败返回错误页

    }

    @PostMapping("/modify")
//    @ApiOperation(value = "用户修改自身信息")
    public String modify(@ModelAttribute("user")User user){
//        if(userService.modify(user))
//            return "workSpace";
//        else
            return "wrong";
    }
}
