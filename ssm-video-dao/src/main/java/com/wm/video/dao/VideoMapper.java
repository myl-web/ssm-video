package com.wm.video.dao;

import com.wm.video.pojo.QueryVo;
import com.wm.video.pojo.Video;
import com.wm.video.pojo.VideoExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMapper {

    List<Video> selectAll(QueryVo queryVo);

    int countByExample(VideoExample example);

    int deleteByExample(VideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByExampleWithBLOBs(VideoExample example);

    List<Video> selectByExample(VideoExample example);

    /*List<Video> selectAll(QueryVo queryVo);*/

    Video selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExampleWithBLOBs(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExample(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKeyWithBLOBs(Video record);

    int updateByPrimaryKey(Video record);

}