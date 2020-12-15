package com.chenqi.community.controller;

import com.chenqi.community.entity.RegisterRequest;
import com.chenqi.community.entity.User;
import com.chenqi.community.service.UserService;
import com.chenqi.community.utils.ValidateImageCodeUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 12076
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 验证码
     * @param session
     * @param response
     */

    @GetMapping("/code")
    public void  code(HttpSession session, HttpServletResponse response){
        ServletOutputStream outputStream=null;
        try{
            //获取验证码
            String securityCode = ValidateImageCodeUtils.getSecurityCode();
            BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
            //存入session作用域
            session.setAttribute("code", securityCode);
            //响应图片
             outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                outputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 登陆
     * @param username
     * @param password
     * @param modelAndView
     * @return
     */

    @PostMapping("/login")
    public ModelAndView login(String username, String password,String code ,ModelAndView modelAndView, HttpSession session){
        User user = userService.login(username, password);
        Object codeImg = session.getAttribute("code");
        if (user==null){
            modelAndView.setViewName("redirect:/toLogin?msg=username or password is wrong! please try it again");
            return modelAndView;
        }else if (code.equals(codeImg)){
            session.setAttribute("user", user);
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }else {
            modelAndView.setViewName("redirect:/toLogin?msg=code is wrong! please try it again");
            return modelAndView;
        }

    }

    /**
     * 注册的方法
     * @param registerRequest
     * @param modelAndView
     * @param avatar
     * @return
     */
    @PostMapping("/register")
    public ModelAndView save(RegisterRequest registerRequest, MultipartFile avatar, ModelAndView modelAndView, HttpServletRequest request){
         try {  //封装用户的基本信息
             User user = new User();
             user.setUsername(registerRequest.getUsername());
             user.setPassword(registerRequest.getPassword());
             user.setEmail(registerRequest.getEmail());
             user.setGitAddress(registerRequest.getGitAddress());
             //处理头像上传
             String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/avatars";
             File dir = new File(realPath);
             if (!dir.exists()) dir.mkdirs();
             System.out.println(avatar.getOriginalFilename());
             //修改原头像文件名
             String extension = FilenameUtils.getExtension(avatar.getOriginalFilename());
             String prefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
             String newFileName = prefix + "." + extension;
             //写入
             avatar.transferTo(new File(dir, newFileName));
             //将图片相对于服务器的路径存入数据库
             String dirStr = realPath.substring(realPath.lastIndexOf("/"));
             String dataImgPath = dirStr + "/" + newFileName;
             System.out.println(dataImgPath);
             user.setAvatar(dataImgPath);
             userService.save(user);
             modelAndView.setViewName("redirect:/toLogin");
             return modelAndView;
         }catch (Exception e){
             e.printStackTrace();
            modelAndView.setViewName("redirect:/toRegister?msg=register fail please try again");
         }
        return modelAndView;
    }
}
