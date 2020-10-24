package com.wm.video.controller;

import com.wm.video.pojo.User;
import com.wm.video.service.UserService;
import com.wm.video.utils.ImageCut;
import com.wm.video.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //登录
    @RequestMapping("loginUser")
    @ResponseBody
    public String loginUser(User user, HttpServletRequest request) {

        List<User> userList = userService.findUser(user);
        for (User user1 : userList) {
            if (null != user1) {
                HttpSession session = request.getSession();
                session.setAttribute("userAccount", user.getEmail());
                return "success";
            }
            return "failed";
        }
        return "failed";
    }


    //退出
    @RequestMapping("loginOut")
    @ResponseBody
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("userAccount");

        return "success";
    }

    //退出
    @RequestMapping("loginOut2")
    public String loginOut2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("userAccount");

        return "redirect:/subject/findAll";
    }

    //注册用户
    @RequestMapping("insertUser")
    @ResponseBody
    public String insertUser(User user, HttpServletRequest request) {
        user.setCreatetime(new Date());
        userService.addUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("userAccount", user.getEmail());
        return "success";
    }

    // 验证邮箱是否已经注册
    @RequestMapping("validateEmail")
    @ResponseBody
    public String validateEmail(String email) {
        User user = userService.findByEmail(email);
        if (null == user) {
            return "success";
        }
        return "hasUser";
    }

    //个人中心
    @RequestMapping("showMyProfile")
    public String showMyProfile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userService.findByEmail((String) session.getAttribute("userAccount"));
        session.setAttribute("user", user);
        return "/before/my_profile.jsp";
    }

    //更改个人资料
    @RequestMapping("changeProfile")
    public String changeProfile() {
        return "/before/change_profile.jsp";
    }

    //修改用户资料
    @RequestMapping("updateUser")
    public String updateUser(String nickname, String sex, String birthday, String address, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setNickname(nickname);
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setAddress(address);
        userService.updateUser(user);
        return "redirect:/user/showMyProfile";
    }

    //忘记密码
    @RequestMapping("forgetPassword")
    public String forgetPassword() {
        return "/before/forget_password.jsp";

    }

    //密码安全
    @RequestMapping("passwordSafe")
    public String passwordSafe() {
        return "/before/password_safe.jsp";
    }

    //验证密码
    @RequestMapping("validatePassword")
    @ResponseBody
    public String validatePassword(String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (password.equals(user.getPassword())) {
            return "success";
        }
        return "failed";
    }

    //更改密码
    @RequestMapping("updatePassword")
    public String updatePassword(String newPassword, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setPassword(newPassword);
        userService.updateUser(user);
        return "redirect:/user/showMyProfile";

    }

    //发送邮箱
    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(String email, HttpServletRequest request) {
        if ("success".equals(validateEmail(email))) {
            return "hasNoUser";
        }
        String code = MailUtils.getValidateCode(6);
        boolean sendMail = MailUtils.sendMail(email, "随机验证码是:" + code, "无需回复");
        if (sendMail) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("code", code);
            return "success";
        }
        return "sendMail";
    }

    //判断邮箱验证
    @RequestMapping("validateEmailCode")
    public String validateEmailCode(String email, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionEmail = (String) session.getAttribute("email");
        String sessionCode = (String) session.getAttribute("code");
        if (sessionEmail.equals(email) && sessionCode.equals(code)) {
            return "/before/reset_password.jsp";
        }
        return "redirect:/user/forgetPassword";
    }

    //更改密码
    @RequestMapping("restPassword")
    public String restPassword(String email, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("email");
        session.removeAttribute("code");
        User user = userService.findByEmail(email);
        if (null != user) {
            user.setPassword(password);
            userService.updateUser(user);
            session.setAttribute("userAccount", user.getEmail());
        }
        return "redirect:/index.jsp";
    }

    //更改图像
    @RequestMapping("changeAvatar")
    public String changeAvatar() {
        return  "/before/change_avatar.jsp";
    }

    //上传图片
    @RequestMapping("upLoadImage")
    public String upLoadImage(@RequestParam("image_file") MultipartFile imageFile, String x1, String x2, String y1, String y2, HttpServletRequest request) throws IOException {
        String path = "D:\\JAVA\\java  jar\\img\\apache-tomcat-8.5.41\\webapps\\upload\\";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filename = imageFile.getOriginalFilename();
        filename = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + filename;
        imageFile.transferTo(new File(path, filename));

        int x1Int = (int) Double.parseDouble(x1);
        int x2Int = (int) Double.parseDouble(x2);
        int y1Int = (int) Double.parseDouble(y1);
        int y2Int = (int) Double.parseDouble(y2);
        new ImageCut().cutImage(path + "/" + filename, x1Int, y1Int, x2Int - x1Int, y2Int - y1Int);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setImgurl(filename);
        userService.updateUser(user);

        return "redirect:/user/showMyProfile";
    }









}
