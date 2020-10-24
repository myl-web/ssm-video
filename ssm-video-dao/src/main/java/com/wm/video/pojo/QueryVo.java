package com.wm.video.pojo;



public class QueryVo {

    private String queryTitle;

    private String querySpeakerId;

    private String queryCourseId;

    public String getQueryTitle() {
        return queryTitle;
    }

    public void setQueryTitle(String queryTitle) {
        this.queryTitle = queryTitle;
    }

    public String getQuerySpeakerId() {
        return querySpeakerId;
    }

    public void setQuerySpeakerId(String querySpeakerId) {
        this.querySpeakerId = querySpeakerId;
    }

    public String getQueryCourseId() {
        return queryCourseId;
    }

    public void setQueryCourseId(String queryCourseId) {
        this.queryCourseId = queryCourseId;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "queryTitle='" + queryTitle + '\'' +
                ", querySpeakerId='" + querySpeakerId + '\'' +
                ", queryCourseId='" + queryCourseId + '\'' +
                '}';
    }
}
