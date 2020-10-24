package com.wm.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.video.pojo.*;
import com.wm.video.service.CourseService;
import com.wm.video.service.SpeakerService;
import com.wm.video.service.SubjectService;
import com.wm.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private SpeakerService speakerService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    //展示视频及分页和多条件查询
    @RequestMapping("list")
    public String list(String pageNum, QueryVo queryVo, Model model) {

        model.addAttribute("queryVo", queryVo);

        List<Video> videoList = videoService.findAll(queryVo);
        List<Speaker> speakerList = speakerService.findAll();
        List<Course> courseList = courseService.findAll();
        int size = videoList.size();

        if (pageNum == null || pageNum == "") {
            pageNum = "1";
        }
        int i = Integer.parseInt(pageNum);

        PageHelper.startPage(i,4);
        List<Video> videoList1 = videoService.findAll(queryVo);
        PageInfo<Video> videoPageInfo = new PageInfo<>(videoList1);

        List<Video> list = videoPageInfo.getList();

        model.addAttribute("page", videoPageInfo);
        model.addAttribute("video", list);
        model.addAttribute("size", size);
        model.addAttribute("speakerList", speakerList);
        model.addAttribute("courseList", courseList);

        return "/behind/videoList.jsp";
    }

    //删除视频
    @RequestMapping("videoDel")
    @ResponseBody
    public String videoDel(String id) {
        videoService.delVideoById(id);

        return "success";
    }

    //添加视频及跳转页面
    @RequestMapping("addVideo")
    public String addVideo(Model model) {

        List<Speaker> speakerList = speakerService.findAll();
        List<Course> courseList = courseService.findAll();
        model.addAttribute("speakerList", speakerList);
        model.addAttribute("courseList",courseList);

        return "/behind/addVideo.jsp";

    }
    //修改视频信息及跳转页面
    @RequestMapping("edit")
    public ModelAndView edit(String id) {

        Video video = videoService.findById(id);
        List<Speaker> speakerList = speakerService.findAll();
        List<Course> courseList = courseService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("video", video);
        modelAndView.addObject("speakerList", speakerList);
        modelAndView.addObject("courseList", courseList);

        modelAndView.setViewName("/behind/addVideo.jsp");
        return modelAndView;
    }

    //添加和修改视频
    @RequestMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(String id, Video video) {

        ModelAndView modelAndView = new ModelAndView();

        if (id != null) {
            videoService.updateVideo(video);
        } else {
            videoService.addVideo(video);
        }
        modelAndView.addObject("video", video);
        modelAndView.setViewName("redirect:/video/list");

        return modelAndView;
    }

    //批量删除信息
    @RequestMapping("delBatchVideos")
    public ModelAndView delBatchVideos(Integer [] ids){
        ModelAndView modelAndView = new ModelAndView();
        for(Integer id:ids){
            String s = String.valueOf(id);
            videoService.delVideoById(s);
        }

        modelAndView.setViewName("redirect:/video/list");
       return modelAndView;
    }

    @RequestMapping("showVideo")
    public ModelAndView showVideo(String videoId,String subjectName) {
        ModelAndView modelAndView = new ModelAndView();


        Video one = videoService.findById(videoId);

        Integer speakerId = one.getSpeakerId();
        Speaker speaker = speakerService.findSpeaker(speakerId);

        List<Subject> allSubject = subjectService.findAll();
        int subjectId = 0;
        for (Subject subject : allSubject) {
            if (subject.getSubjectName().equals(subjectName)) {
                subjectId = subject.getId();
            }
        }
        List<Course> courses = courseService.checkCourse(subjectId);

        Integer courseId = one.getCourseId();
        String s1 = String.valueOf(courseId);
        Course course = courseService.selectCourseById(s1);

        QueryVo queryVo = new QueryVo();
        String s = String.valueOf(courseId);
        queryVo.setQueryCourseId(s);
        List<Video> videos = videoService.findAll(queryVo);
        course.setVideos(videos);

        modelAndView.addObject("course",course);
        modelAndView.addObject("speaker",speaker);
        modelAndView.addObject("subjectName",subjectName);
        modelAndView.addObject("video",one);
        modelAndView.setViewName("/before/section.jsp");
        return modelAndView;
    }

}
