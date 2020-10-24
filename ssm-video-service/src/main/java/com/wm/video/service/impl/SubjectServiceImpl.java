package com.wm.video.service.impl;

import com.wm.video.dao.SubjectMapper;
import com.wm.video.pojo.Subject;
import com.wm.video.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> findAll() {

        return subjectMapper.selectByExample(null);
    }

    @Override
    public Subject findById(String id) {
        return subjectMapper.selectByPrimaryKey(Integer.parseInt(id));
    }
}
