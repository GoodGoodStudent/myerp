package com.puhuanyu.erp.myerp.controller;

import com.puhuanyu.erp.myerp.service.EmpService;
import com.puhuanyu.erp.myerp.util.CodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
public class DepController
{
    @Autowired
    private EmpService empService;
    @Autowired
    private CodeUtil codeUtil;

    public static final String CODE = "CODE";//session的验证码

    /**
     * @Description 默认初始请求跳转到登陆页面
     * @Param []
     * @return java.lang.String
     * @Author 忠哥
     * @Date 2020-1-6 14:42
     */
    @RequestMapping(value = "/")
    public String index(){
        return "login.html";
    }

    /**
     * @Description 跳转到未授权界面
     * @Param []
     * @return java.lang.String
     * @Author 忠哥
     * @Date 2020-1-9 16:19
     */
    @RequestMapping(value = "/noAuth")
    public String noAuth(){
        return "noAuth.html";
    }

    /**
     * @Description 验证码生成器，将图片通过流的形式输出到客户端
     * @Param [request, response]
     * @return void
     * @Author 忠哥
     * @Date 2020-1-9 15:07
     */
    @RequestMapping("/code.jpg")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        // 不缓存此内容
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
            HttpSession session = request.getSession();
            StringBuffer code = new StringBuffer();
            BufferedImage image = codeUtil.getRandomCodeImage(code);//通过调用工具类来获取图片
            session.removeAttribute(CODE);
            session.setAttribute(CODE, code.toString());//将验证码图片的内容保存在session
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 登陆请求
     * @Param [id,password,code,remeber,request] id、password、code、remeber分别为前端传来的账号，密码，验证码，请记住我
     * @return java.lang.String
     * @Author 忠哥
     * @Date 2020-1-6 11:00
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String id, String password, String code, boolean remeber,HttpServletRequest request){
        //验证码验证
        HttpSession session = request.getSession();//获取session中的验证码
        if(!code.equalsIgnoreCase(String.valueOf(session.getAttribute(CODE)))){
            return "codeError";
        }
        System.out.println(remeber);
        Subject subject = SecurityUtils.getSubject();//1.获取主体
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(id, password, remeber);//2.封装用户数据
        try {
            subject.login(usernamePasswordToken);//3.执行登陆方法------>4.认证身份（CustomRealm中的doGetAuthenticationInfo方法）
        }catch (UnknownAccountException e){//账号异常
            e.printStackTrace();
            return "accountError";
        }catch (IncorrectCredentialsException e){//密码异常
            e.printStackTrace();
            return "passwordError";
        }catch (AuthenticationException e){//账号密码异常
            e.printStackTrace();
            return "accountOrPasswordError";
        }catch (AuthorizationException e){//权限异常
            e.printStackTrace();
            return "powerError";
        }
        //查看是否认证成功
        if(subject.isAuthenticated()){
            return "index.html";
        }else{
            return "login.html";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "login";
    }}
