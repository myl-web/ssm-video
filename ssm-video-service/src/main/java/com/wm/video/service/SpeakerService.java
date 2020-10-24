package com.wm.video.service;

import com.wm.video.pojo.Speaker;

import java.util.List;

public interface SpeakerService {

    List<Speaker> findAll();

    Speaker delSpeakerById(String id);

    void addSpeaker(Speaker speaker);
    void updateSpeaker(Speaker speaker);

    Speaker findById(String id);

    Speaker findSpeaker(Integer id);
}
