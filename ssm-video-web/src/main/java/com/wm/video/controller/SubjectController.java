package com.wm.video.controller;

import com.wm.video.pojo.Subject;
import com.wm.video.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("findAll")
    public String findAll(HttpServletRequest request){
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        List<Subject> subjectList = subjectService.findAll();
        servletContext.setAttribute("subject", subjectList);

        return "/before/index.jsp";
    }
}
