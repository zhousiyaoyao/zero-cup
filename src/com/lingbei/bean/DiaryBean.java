package com.lingbei.bean;

import java.security.Timestamp;


/**
 * Created by gxy on 2016/12/4.
 */
public class DiaryBean {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    private String content;

    private String createtime;

    public String getDiaryphoto() {
        return diaryphoto;
    }

    public void setDiaryphoto(String diaryphoto) {
        this.diaryphoto = diaryphoto;
    }

    private String diaryphoto;

}
