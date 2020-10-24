package com.wm.video.controller;

import com.wm.video.pojo.Admin;
import com.wm.video.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/loginView")
    public String loginView() {
        return "/behind/login.jsp";
    }

    //后台登录
    @RequestMapping("login")
    @ResponseBody
    public String login( String username, String password, HttpSession session) {

        List<Admin> list = adminService.findAdmin(username, password);
        if (list.size() == 0) {
            return "failed";
        } else {
            session.setAttribute("admin", list);
            return "success";
        }
    }


    //后台退出
    @RequestMapping("exit")
    public String exit(HttpSession session) {

        session.removeAttribute("admin");

        return "redirect:/admin/loginView";
    }

}
