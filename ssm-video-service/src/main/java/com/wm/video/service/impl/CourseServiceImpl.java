package com.wm.video.service.impl;

import com.wm.video.dao.CourseMapper;
import com.wm.video.pojo.Course;
import com.wm.video.pojo.CourseExample;
import com.wm.video.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.selectByExample(null);
    }

    @Override
    public Course selectCourseById(String videoId) {
        return courseMapper.selectByPrimaryKey(Integer.parseInt(videoId));
    }

    @Override
    public List<Course> checkCourse(int sid) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andSubjectIdEqualTo(sid);
        List<Course> courses = courseMapper.selectByExample(courseExample);
        return courses;
    }
}
