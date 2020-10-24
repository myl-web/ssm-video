package com.wm.video.controller;

import com.wm.video.pojo.Course;
import com.wm.video.pojo.QueryVo;
import com.wm.video.pojo.Subject;
import com.wm.video.pojo.Video;
import com.wm.video.service.CourseService;
import com.wm.video.service.SubjectService;
import com.wm.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private VideoService videoService;

    @RequestMapping("course/{id}")
    public ModelAndView course(@PathVariable(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Video> serviceAll = videoService.findAll2();
        List<Subject> allSubject = subjectService.findAll();
        List<Course> allCourse = courseService.findAll();

        //找到当前需求课程
        Subject subject = subjectService.findById(id);
        //找到course对应课程
        List<Course> courses = courseService.checkCourse(subject.getId());

        for(int i=0; i < courses.size() ;i++ ) {
            QueryVo queryVo = new QueryVo();
            Integer id1 = courses.get(i).getId();
            String s = String.valueOf(id1);
            queryVo.setQueryCourseId(s);
            List<Video> videos = videoService.findAll(queryVo);
            String videoList = "videoList" + "i";
            courses.get(i).setVideos(videos);
        }
        modelAndView.addObject("courseList",courses);
        modelAndView.addObject("subjectList",allSubject);
        modelAndView.addObject("subject",subject);
        modelAndView.setViewName("/before/course.jsp");
        return modelAndView;
    }
}
