package com.wm.video.dao;


import com.wm.video.pojo.Speaker;
import com.wm.video.pojo.SpeakerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpeakerMapper {
    int countByExample(SpeakerExample example);

    int deleteByExample(SpeakerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Speaker record);

    int insertSelective(Speaker record);

    List<Speaker> selectByExampleWithBLOBs(SpeakerExample example);

    List<Speaker> selectByExample(SpeakerExample example);

    Speaker selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Speaker record, @Param("example") SpeakerExample example);

    int updateByExampleWithBLOBs(@Param("record") Speaker record, @Param("example") SpeakerExample example);

    int updateByExample(@Param("record") Speaker record, @Param("example") SpeakerExample example);

    int updateByPrimaryKeySelective(Speaker record);

    int updateByPrimaryKeyWithBLOBs(Speaker record);

    int updateByPrimaryKey(Speaker record);
}