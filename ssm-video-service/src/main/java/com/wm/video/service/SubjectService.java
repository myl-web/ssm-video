package com.wm.video.service;

import com.wm.video.pojo.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> findAll();

    Subject findById(String id);
}
