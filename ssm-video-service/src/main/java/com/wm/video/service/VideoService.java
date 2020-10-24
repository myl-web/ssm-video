package com.wm.video.service;


import com.wm.video.pojo.QueryVo;
import com.wm.video.pojo.Video;

import java.util.List;

public interface VideoService {

    List<Video> findAll(QueryVo queryVo);
    List<Video> findAll2();

    void delVideoById(String id);

    void addVideo(Video video);

    void updateVideo(Video video);

    Video findById(String id);


}
