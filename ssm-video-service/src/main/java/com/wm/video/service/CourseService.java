package com.wm.video.service;

import com.wm.video.pojo.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course selectCourseById(String videoId);

    List<Course> checkCourse(int sid);

}
