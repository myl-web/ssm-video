package com.wm.video.service.impl;

import com.wm.video.dao.SpeakerMapper;
import com.wm.video.pojo.Speaker;
import com.wm.video.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerMapper speakerMapper;


    @Override
    public List<Speaker> findAll() {
        return speakerMapper.selectByExample(null);
    }

    @Override
    public Speaker delSpeakerById(String id) {
         speakerMapper.deleteByPrimaryKey(Integer.parseInt(id));

         return null;
    }

    @Override
    public void addSpeaker(Speaker speaker) {
        speakerMapper.insert(speaker);
    }

    @Override
    public void updateSpeaker(Speaker speaker) {

        speakerMapper.updateByPrimaryKey(speaker);

    }

    @Override
    public Speaker findById(String id) {

        return speakerMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public Speaker findSpeaker(Integer id) {
        return speakerMapper.selectByPrimaryKey(id);
    }
}
