package com.lingbei.bean;

import java.security.Timestamp;

/**
 * Created by gxy on 2016/12/3.
 */
public class CommentBean {
    private int result=0;
    private String message="";

    private UserLoginBean user;
    private String content;
    private Timestamp createtime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public UserLoginBean getUser() {
        return user;
    }

    public void setUser(UserLoginBean user) {
        this.user = user;
    }

    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
