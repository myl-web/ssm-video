package com.wm.video.service.impl;

import com.wm.video.dao.VideoMapper;
import com.wm.video.pojo.QueryVo;
import com.wm.video.pojo.Video;
import com.wm.video.pojo.VideoExample;
import com.wm.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;


    @Override
    public List<Video> findAll(QueryVo queryVo) {
        return videoMapper.selectAll(queryVo);
    }

    @Override
    public List<Video> findAll2() {
        return videoMapper.selectByExample(null);
    }


    @Override
    public void delVideoById(String id) {

       videoMapper.deleteByPrimaryKey(Integer.parseInt(id));

    }

    @Override
    public void addVideo(Video video) {
        videoMapper.insert(video);
    }

    @Override
    public void updateVideo(Video video) {
        videoMapper.updateByPrimaryKey(video);
    }

    @Override
    public Video findById(String id) {
        return videoMapper.selectByPrimaryKey(Integer.parseInt(id));
    }


}
