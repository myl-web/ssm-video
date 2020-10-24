package com.wm.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.video.pojo.Speaker;
import com.wm.video.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;


    //speaker页面展示与分页
    @RequestMapping("showSpeakerList")
    public ModelAndView showSpeakerList(String pageNum) {

        List<Speaker> speakerList = speakerService.findAll();



        int size = speakerList.size();

        if (pageNum == null) {
            pageNum = "1";
        }

        int i = Integer.parseInt(pageNum);

        PageHelper.startPage(i, 4);
        List<Speaker> speakers = speakerService.findAll();
        PageInfo<Speaker> speakerPageInfo = new PageInfo<>(speakers);
        List<Speaker> list = speakerPageInfo.getList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page",speakerPageInfo);
        modelAndView.addObject("speaker",list);
        modelAndView.addObject("size",size);

        modelAndView.setViewName("/behind/speakerList.jsp");

        return modelAndView;
    }


    //删除数据
    @RequestMapping("speakerDel")
    @ResponseBody
    public String speakerDel(String id, Model model) {

        Speaker speaker = speakerService.delSpeakerById(id);

        model.addAttribute("speaker", speaker);
        return "success";
    }

    //添加及跳转页面
    @RequestMapping("addSpeaker")
    public String addSpeaker() {

        return "/behind/addSpeaker.jsp";

    }

    //修改及跳转页面
    @RequestMapping("edit")
    public ModelAndView edit(String id) {

        Speaker speaker = speakerService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("speaker", speaker);
        modelAndView.setViewName("/behind/addSpeaker.jsp");

        return modelAndView;
    }

    //添加和修改数据
    @RequestMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(String id, Speaker  speaker) {

        ModelAndView modelAndView = new ModelAndView();

        if (id != null) {
            speakerService.updateSpeaker(speaker);
        } else {
            speakerService.addSpeaker(speaker);
        }
        modelAndView.addObject("speaker", speaker);
        modelAndView.setViewName("redirect:/speaker/showSpeakerList");

        return modelAndView;

    }

}
